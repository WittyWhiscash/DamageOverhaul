package mod.wittywhiscash.damageoverhaul.mixin.item;

import com.google.common.collect.Sets;
import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import mod.wittywhiscash.damageoverhaul.api.DamageCondition;
import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.damage.DamageAttribute;
import mod.wittywhiscash.damageoverhaul.common.database.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    @Shadow
    abstract Item getItem();

    @Environment(EnvType.CLIENT)
    @Inject(method = "getTooltip", at = @At("RETURN"))
    public void addDamageInfoToTooltip(@Nullable PlayerEntity playerEntity, TooltipContext context, CallbackInfoReturnable<List<Text>> cir) {
        List<Text> additionalText = new LinkedList<>();
        if (ArmorResistances.values().contains(getItem())) {
            if (Screen.hasControlDown()) {
                Map<DamageType, DamageAttribute> resistanceSpread = DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().getResistanceSpread(getItem());
                for (Map.Entry<DamageType, DamageAttribute> entry : resistanceSpread.entrySet()) {
                    if (entry.getValue().getModifier() > 1) {
                        additionalText.add(new TranslatableText("tooltip.damageoverhaul.resistance.multi", StringUtils.capitalize(entry.getKey().getRegistryName()), DamageOverhaul.DF.format(entry.getValue().getModifier())).formatted(Formatting.BLUE));
                    }
                    else {
                        additionalText.add(new TranslatableText("tooltip.damageoverhaul.resistance.single", StringUtils.capitalize(entry.getKey().getRegistryName()), DamageOverhaul.DF.format(entry.getValue().getModifier())).formatted(Formatting.BLUE));
                    }
                }
            }
            else {
                additionalText.add(new TranslatableText("tooltip.damageoverhaul.resistance.holdControl").formatted(Formatting.YELLOW));
            }
        }
        if (ToolDamages.values().contains(getItem())) {
            if (Screen.hasShiftDown()) {
                Map<DamageType, DamageAttribute> damageSpread = DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(getItem());
                for (Map.Entry<DamageType, DamageAttribute> entry : damageSpread.entrySet()) {
                    additionalText.add(new TranslatableText("tooltip.damageoverhaul.damage", entry.getValue().getModifier() * 100, StringUtils.capitalize(entry.getKey().getRegistryName())).formatted(Formatting.BLUE));
                }
            }
            else {
                additionalText.add(new TranslatableText("tooltip.damageoverhaul.damage.holdShift").formatted(Formatting.YELLOW));
            }
        }
        if (getItem() instanceof SpawnEggItem) {
            EntityType<?> type =((SpawnEggItem) getItem()).getEntityType(null);
            if (EntityDamages.values().contains(type)) {
                if (Screen.hasShiftDown()) {
                    Map<DamageType, DamageAttribute> damageSpread = DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(type);
                    for (Map.Entry<DamageType, DamageAttribute> entry : damageSpread.entrySet()) {
                        additionalText.add(new TranslatableText("tooltip.damageoverhaul.damage", DamageOverhaul.DF.format(entry.getValue().getModifier() * 100), StringUtils.capitalize(entry.getKey().getRegistryName())).formatted(Formatting.BLUE));
                    }
                } else {
                    additionalText.add(new TranslatableText("tooltip.damageoverhaul.damage.holdShift").formatted(Formatting.YELLOW));
                }
            }
            if (EntityResistances.values().contains(type))
                if (Screen.hasControlDown()) {
                    Map<DamageType, DamageAttribute> resistanceSpread = DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().getResistanceSpread(type);
                    Set<DamageCondition> conditionsWModifiers = Sets.newHashSet(DamageCondition.RESISTANT, DamageCondition.WEAK);
                    for (Map.Entry<DamageType, DamageAttribute> entry : resistanceSpread.entrySet()) {
                        if (conditionsWModifiers.contains(entry.getValue().getDamageCondition())) {
                            additionalText.add(new TranslatableText("tooltip.damageoverhaul.mobResistance.value", StringUtils.capitalize(entry.getKey().getRegistryName()), entry.getValue().getDamageCondition().name(), DamageOverhaul.DF.format(entry.getValue().getDamageCondition() == DamageCondition.RESISTANT ? (1 - entry.getValue().getModifier()) * 100 : (1 + entry.getValue().getModifier()) * 100)).formatted(Formatting.BLUE));
                        }
                        else {
                            additionalText.add(new TranslatableText("tooltip.damageoverhaul.mobResistance", StringUtils.capitalize(entry.getKey().getRegistryName()), entry.getValue().getDamageCondition().name()).formatted(Formatting.BLUE));
                        }
                    }
                } else {
                    additionalText.add(new TranslatableText("tooltip.damageoverhaul.resistance.holdControl").formatted(Formatting.YELLOW));
                }
            }
        cir.getReturnValue().addAll(additionalText);
    }
}
