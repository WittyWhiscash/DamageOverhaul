package mod.wittywhiscash.damageoverhaul.common.damage;

import com.google.common.util.concurrent.AtomicDouble;
import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.database.DamageTypes;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.impl.networking.ServerSidePacketRegistryImpl;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import org.apache.logging.log4j.Level;

import java.util.*;

public class DamageUtils {

    public static Map<DamageType, Float> getDamageAfterTypedArmor(DamageSource source, float amount, float armorAmount, float armorToughness, Entity attacker, LivingEntity target) {
        DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), "Performing armor calc...");
        Map<DamageType, Float> damageArray = new HashMap<>();
        ItemStack heldStack = attacker instanceof LivingEntity ? ((LivingEntity) attacker).getMainHandStack() : ItemStack.EMPTY;
        boolean containsStack = DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().contains(heldStack.getItem());
        Map<DamageType, DamageAttribute> typedDamageSpread = Objects.nonNull(source.getSource()) ? containsStack ?
                DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(heldStack.getItem()) :
                DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(attacker.getType()) :
                DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source);
        if (!source.bypassesArmor()) {
            List<Float> damages = new ArrayList<>();
            float magicDamageMod = 0;
            if (heldStack.hasEnchantments()) {
                magicDamageMod += DamageOverhaul.CONFIG.DAMAGE.getMagicModifier();
                damages.add(amount * magicDamageMod);
            }
            if (magicDamageMod > 0) {
                damageArray.put(DamageTypes.valueOf(DamageType.MAGIC), amount * magicDamageMod);
            }
            for (DamageType type : typedDamageSpread.keySet()) {
                damages.add((amount * (1 - magicDamageMod) * typedDamageSpread.get(type).getModifier()));
            }
            float standardDeviation = calculateStandardDeviation(damages);
            for (DamageType type : typedDamageSpread.keySet()) {
                if (magicDamageMod > 0) {
                    amount *= 1 - magicDamageMod;
                }
                float damageAfterTyping = amount * typedDamageSpread.get(type).getModifier();
                AtomicDouble typedArmorValue = new AtomicDouble(0);
                AtomicDouble naturalArmorValue = new AtomicDouble(armorAmount);
                target.getArmorItems().forEach(armorItem -> {
                    if (armorItem.getItem() instanceof ArmorItem) {
                        if (DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().contains(armorItem.getItem()) && DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().getResistanceSpread(armorItem.getItem()).containsKey(type)) {
                            typedArmorValue.addAndGet(DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().getResistanceSpread(armorItem.getItem()).get(type).getModifier());
                            naturalArmorValue.addAndGet(-(DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().getResistanceSpread(armorItem.getItem()).get(type).getModifier()));
                        }
                    }
                });
                if (typedArmorValue.get() <= 0) {
                    DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), String.format("Damage Type: %s, After Armor: %f", type.getRegistryName(), damageAfterTyping));
                    damageArray.put(type, damageAfterTyping);
                }
                else {
                    float damageMagnitude = DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.getDamageMagnitude();
                    float damageNumDecreaseMagnitude = DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.getDamageNumDecreaseMagnitude();
                    float damageTypeMultiplier = DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.getDamageTypeMultiplier();
                    float toughnessEffectiveness = DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.getToughnessEffectiveness();
                    float toughnessReductionEffectiveness = DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.getToughnessEffectivenessReduction();

                    float effectiveArmor = (float) Math.max(typedArmorValue.get() / 20, typedArmorValue.get() - (damageMagnitude * (amount - (damageNumDecreaseMagnitude * (damages.size() - 1) / (1 + damageTypeMultiplier * standardDeviation))) * ((toughnessEffectiveness + armorToughness) / (toughnessEffectiveness + toughnessReductionEffectiveness * armorToughness))));
                    DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), String.format("Total armor: %f, Effective Armor: %2f", typedArmorValue.get(), effectiveArmor));

                    float finalReduction = DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.getFinalReduction();
                    float baseDecay = DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.getBaseDecay();
                    float toughnessDecay = DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.getToughnessDecay();
                    float toughnessReduction = DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.getToughnessReduction();

                    float reductionMultiplier = (float) Math.exp((-baseDecay - toughnessDecay * armorToughness) * (effectiveArmor + naturalArmorValue.get())) * (1 - finalReduction) + finalReduction - toughnessReduction * armorToughness;

                    float damageAfterArmor = damageAfterTyping * reductionMultiplier;

                    DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), String.format("Damage Type: %s, After Armor: %f", type.getRegistryName(), damageAfterArmor));
                    damageArray.put(type, damageAfterArmor);
                }
            }
        }
        else {
            for (DamageType type : typedDamageSpread.keySet()) {
                double damageToPut = amount * typedDamageSpread.get(type).getModifier();
                DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), String.format("Damage type: %s, After Armor: %f", type.getRegistryName(), damageToPut));
                damageArray.put(type, (float) damageToPut);
            }
        }
        for (DamageType type : damageArray.keySet()) {
            Map<DamageType, DamageAttribute> resistanceSpread = DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().getResistanceSpread(target.getType());
            if (resistanceSpread.containsKey(type)) {
                float damageToModify = damageArray.get(type);
                switch (resistanceSpread.get(type).getDamageCondition()) {
                    case IMMUNE:
                        damageToModify = 0;
                        damageArray.replace(type, damageToModify);
                        break;
                    case RESISTANT:
                        damageToModify *= 1 - resistanceSpread.get(type).getModifier();
                        damageArray.replace(type, damageToModify);
                        break;
                    case WEAK:
                        damageToModify *= 1 + resistanceSpread.get(type).getModifier();
                        damageArray.replace(type, damageToModify);
                        break;
                    case VULNERABLE:
                        damageToModify *= 2;
                        for (ServerPlayerEntity entity : PlayerLookup.tracking(target)) {
                            ServerSidePacketRegistryImpl.INSTANCE.sendToPlayer(entity, new ParticleS2CPacket(DamageOverhaul.VULNERABLE_PARTICLE, false, target.getX(), target.getY(), target.getZ(), 0.4f, 0.4f, 0.4f, 1.0F, target.getRandom().nextInt(3)));
                            DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), "Spawned particles!");
                        }
                        damageArray.replace(type, damageToModify);
                        break;
                    default:
                        break;
                }
            }
            DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), String.format("Damage type: %s, After resistance/weakness: %f", type.getRegistryName(), damageArray.get(type)));
        }
        return damageArray;
    }

    public static float getDamageAfterEnchantments(DamageSource source, Map<DamageType, Float> damageSpread, Entity attacker, LivingEntity target) {
        DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), "Performing enchantment calc...");
        if (!source.isUnblockable()) {
            float totalDamage = 0;
            for (DamageType type : damageSpread.keySet()) {
                totalDamage += damageSpread.get(type);
            }
            DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), String.format("Damage before enchantment/status calc: %f", totalDamage));
            Map<DamageType, Integer> enchantmentResistances = new HashMap<>();
            target.getArmorItems().forEach(armorItem -> {
                for (Enchantment enchantment : DamageOverhaul.ATTRIBUTE_DATABASE.getEnchantmentResistanceDatabase().values()) {
                    if (armorItem.hasEnchantments() && EnchantmentHelper.get(armorItem).containsKey(enchantment)) {
                        for (DamageType type : DamageOverhaul.ATTRIBUTE_DATABASE.getEnchantmentResistanceDatabase().getResistanceSpread(enchantment).keySet()) {
                            if (enchantmentResistances.containsKey(type)) {
                                int prevValue = enchantmentResistances.get(type);
                                enchantmentResistances.replace(type, prevValue + (DamageOverhaul.ATTRIBUTE_DATABASE.getEnchantmentResistanceDatabase().getResistanceSpread(enchantment).get(type) * EnchantmentHelper.getLevel(enchantment, armorItem)));
                            }
                            else {
                                enchantmentResistances.put(type, DamageOverhaul.ATTRIBUTE_DATABASE.getEnchantmentResistanceDatabase().getResistanceSpread(enchantment).get(type) * EnchantmentHelper.getLevel(enchantment, armorItem));
                            }
                        }
                    }
                }
            });
            DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), enchantmentResistances.toString());
            for (DamageType type : damageSpread.keySet()) {
                if (enchantmentResistances.containsKey(type)) {
                    float damageToModify = damageSpread.get(type);
                    int protectionLevel = enchantmentResistances.get(type);
                    damageToModify *= 1 - (protectionLevel / 25.0F);
                    damageSpread.replace(type, damageToModify);
                }
            }
            totalDamage = 0;
            for (DamageType type : damageSpread.keySet()) {
                totalDamage += damageSpread.get(type);
            }
            if (target.hasStatusEffect(StatusEffects.RESISTANCE) && source != DamageSource.OUT_OF_WORLD) {
                int resistanceAmp = (target.getStatusEffect(StatusEffects.RESISTANCE).getAmplifier() + 1) * 5;
                totalDamage *= 1 - (resistanceAmp / 25.0F);
            }
            DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), String.format("Damage after enchantment/status calc: %f", roundToHalf(totalDamage)));
            return roundToHalf(totalDamage);
        }
        else {
            float damageToApply = 0;
            for (DamageType type : damageSpread.keySet()) {
                damageToApply += damageSpread.get(type);
            }
            return roundToHalf(damageToApply);
        }
    }

    private static float roundToHalf(float input) {
        return (float) Math.round(input * 2) / 2;
    }

    private static float calculateStandardDeviation(List<Float> damages) {
        float sum = 0;
        float standardDeviation = 0;
        for (Float damage : damages) {
            sum += damage;
        }
        float mean = sum / damages.size();
        for (Float damage : damages) {
            standardDeviation += Math.pow(damage - mean, 2);
        }
        return (float) Math.sqrt(standardDeviation / damages.size());
    }

}
