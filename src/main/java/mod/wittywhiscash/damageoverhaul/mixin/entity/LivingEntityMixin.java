package mod.wittywhiscash.damageoverhaul.mixin.entity;

import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.util.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;
import java.util.Objects;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow abstract float applyArmorToDamage(DamageSource source, float amount);
    @Shadow abstract float applyEnchantmentsToDamage(DamageSource source, float amount);

    /*
        Null out the armor calculation.

        It's all performed in the method below so we can type out the damage properly
        and the fallback includes the original armor calculation.
    */
    @Redirect(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyArmorToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"))
    private float nullOutArmorCalc(LivingEntity entity, DamageSource source, float amount) {
        return amount;
    }

    /*
        Perform armor and enchantment calculation here. Method contains a fallback
        to the original armor and enchantment calculation in case none of the conditions
        match. We are redirecting it so we can perform our own logic.
    */
    @Redirect(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyEnchantmentsToDamage(Lnet/minecraft/entity/damage/DamageSource;F)F"))
    private float performArmorAndEnchantmentCalc(LivingEntity entity, DamageSource source, float amount) {
        DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), String.format("Original amount: %f", amount));
        if (DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().contains(source) && DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().contains(this.getType())) {
            /*
                Source has neither a source, nor an attacker, and is in our database. This is invoked for environmental damage types,
                like falling, lava, and drowning. Perform logic for the damage source as defined in the database.
            */
            DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), String.format("Database contains source with null attacker, null source, environment type %s", source.getName()));

            Map<DamageType, Float> damageSpreadAfterArmor = DamageUtils.getDamageAfterTypedArmor(source, amount, entity.getArmor(),
                    (float) entity.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS),
                    source.getSource(), entity);
            return DamageUtils.getDamageAfterEnchantments(source, damageSpreadAfterArmor, source.getSource(), entity);
        }
        if (!DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().contains(source)) {
            DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), String.format("Database does NOT contain source with null attacker, null source, environment type %s", source.getName()));
            /*
                Source has neither a source, nor an attacker, and is NOT in our database.

                Damage source is one that has additional properties. Most likely both a source and an attacker.
                Time to do some additional logic, as most likely this is a damage source that is invoked during
                an entity, such as a mob or an arrow, dealing damage to another entity.
            */
            if (Objects.nonNull(source.getAttacker()) && (DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().contains(source.getAttacker().getType()) && DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().contains(this.getType()))) {
                if (Objects.nonNull(source.getSource()) && source.getSource().equals(source.getAttacker())) {
                     /*
                        Source has an attacker and a source, and they are both the same. This is very likely
                        to be a living entity attacking another living entity, or a projectile from a sourceless
                        source, such as a dispenser. Perform different logic as living entities have their own damages
                        based on the weapons they are holding and what entity type they are.
                    */
                    DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), "Source with nonnull attacker and nonnull source, same type!");
                } else {
                    /*
                        Source has both a source and an attacker, but they are different. Very likely that this is a
                        projectile-based entity dealing the damage, fired from a living entity.
                        Logic doesn't change much from other projectile-based damage sources.
                    */
                    DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), "Source with nonnull attacker and nonull source, different type!");
                }
                DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getDamageDebug(), String.format("%s, %2s", source.getAttacker().getType().toString(), source.getSource().getType().toString()));
                Map<DamageType, Float> damageSpreadAfterArmor = DamageUtils.getDamageAfterTypedArmor(source, amount, entity.getArmor(),
                        (float) entity.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS),
                        source.getSource(), entity);
                return DamageUtils.getDamageAfterEnchantments(source, damageSpreadAfterArmor, source.getSource(), entity);
            }
        }
        amount = applyArmorToDamage(source, amount);
        return applyEnchantmentsToDamage(source, amount);
    }
    
}
