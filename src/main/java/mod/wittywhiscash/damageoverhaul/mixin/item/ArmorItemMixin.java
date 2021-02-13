package mod.wittywhiscash.damageoverhaul.mixin.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin extends Item implements Wearable {

    public ArmorItemMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    static UUID[] MODIFIERS;
    @Shadow
    @Mutable
    Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void modifyArmorToughness(ArmorMaterial material, EquipmentSlot slot, Settings settings, CallbackInfo ci) {
        UUID uUID = MODIFIERS[slot.getEntitySlotId()];

        if (material.equals(ArmorMaterials.CHAIN) || material.equals(ArmorMaterials.IRON)) {
            if (slot.equals(EquipmentSlot.CHEST) || slot.equals(EquipmentSlot.LEGS)) {
                ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();

                attributeModifiers.forEach((attribute, modifiers) -> {
                    if (!attribute.equals(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)) {
                        builder.put(attribute, modifiers);
                    }
                });

                builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uUID, "Armor toughness", 1.0D, EntityAttributeModifier.Operation.ADDITION));
                DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("Added 1 armor toughness to material: %s slot: %2s", material.getName(), slot.getName()));
                attributeModifiers = builder.build();
            }
        }
        if (material.equals(ArmorMaterials.DIAMOND) || material.equals(ArmorMaterials.NETHERITE)) {
            if (slot.equals(EquipmentSlot.HEAD) || slot.equals(EquipmentSlot.FEET)) {
                ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();

                attributeModifiers.forEach((attribute, modifiers) -> {
                    if (!attribute.equals(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)) {
                        builder.put(attribute, modifiers);
                    }
                });
                if (material.equals(ArmorMaterials.DIAMOND)) {
                    builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uUID, "Armor toughness", 1.0D, EntityAttributeModifier.Operation.ADDITION));
                }
                else {
                    builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uUID, "Armor toughness", 2.0D, EntityAttributeModifier.Operation.ADDITION));
                }
                DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("Removed 1 armor toughness from material: %s slot: %2s", material.getName(), slot.getName()));
                attributeModifiers = builder.build();
            }
        }
    }
}
