package mod.wittywhiscash.damageoverhaul.client.patchouli;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;

public enum ArmorMaterialDescription {

    LEATHER(ArmorMaterials.LEATHER, ""),
    CHAIN(ArmorMaterials.CHAIN, ""),
    IRON(ArmorMaterials.IRON, ""),
    GOLD(ArmorMaterials.GOLD, ""),
    DIAMOND(ArmorMaterials.DIAMOND, ""),
    NETHERITE(ArmorMaterials.NETHERITE, ""),
    TURTLE(ArmorMaterials.TURTLE, "");

    private final ArmorMaterial armorMaterial;
    private final String materialDescription;

    ArmorMaterialDescription(ArmorMaterial material, String description) {
        this.armorMaterial = material;
        this.materialDescription = description;
    }

    public ArmorMaterial getArmorMaterial() { return armorMaterial; }
    public String getMaterialDescription() { return materialDescription; }
}
