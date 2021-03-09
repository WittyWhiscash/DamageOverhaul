package mod.wittywhiscash.damageoverhaul.common.modules.damage.database.defaults;

import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.util.DamageAttribute;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.database.DamageTypes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.LinkedHashMap;
import java.util.Map;

public enum DefaultToolDamages {
    WOODEN_SWORD(Items.WOODEN_SWORD, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.25F));
    }}),
    WOODEN_SHOVEL(Items.WOODEN_SHOVEL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    WOODEN_PICKAXE(Items.WOODEN_PICKAXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
    }}),
    WOODEN_AXE(Items.WOODEN_AXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.25F));
    }}),
    WOODEN_HOE(Items.WOODEN_HOE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
    }}),
    STONE_SWORD(Items.STONE_SWORD, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.25F));
    }}),
    STONE_SHOVEL(Items.STONE_SHOVEL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    STONE_PICKAXE(Items.STONE_PICKAXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
    }}),
    STONE_AXE(Items.STONE_AXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.5F));
    }}),
    STONE_HOE(Items.STONE_HOE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    GOLDEN_SWORD(Items.GOLDEN_SWORD, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.25F));
    }}),
    GOLDEN_SHOVEL(Items.GOLDEN_SHOVEL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    GOLDEN_PICKAXE(Items.GOLDEN_PICKAXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.25F));
    }}),
    GOLDEN_AXE(Items.GOLDEN_AXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.25F));
    }}),
    GOLDEN_HOE(Items.GOLDEN_HOE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
    }}),
    IRON_SWORD(Items.IRON_SWORD, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(1.0F));
    }}),
    IRON_SHOVEL(Items.IRON_SHOVEL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.25F));
    }}),
    IRON_PICKAXE(Items.IRON_PICKAXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
    }}),
    IRON_AXE(Items.IRON_AXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.5F));
    }}),
    IRON_HOE(Items.IRON_HOE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    DIAMOND_SWORD(Items.DIAMOND_SWORD, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(1.0F));
    }}),
    DIAMOND_SHOVEL(Items.DIAMOND_SHOVEL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.25F));
    }}),
    DIAMOND_PICKAXE(Items.DIAMOND_PICKAXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
    }}),
    DIAMOND_AXE(Items.DIAMOND_AXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.5F));
    }}),
    DIAMOND_HOE(Items.DIAMOND_HOE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    NETHERITE_SWORD(Items.NETHERITE_SWORD, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.25F));
    }}),
    NETHERITE_SHOVEL(Items.NETHERITE_SHOVEL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.25F));
    }}),
    NETHERITE_PICKAXE(Items.NETHERITE_PICKAXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.25F));
    }}),
    NETHERITE_AXE(Items.NETHERITE_AXE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.25F));
    }}),
    NETHERITE_HOE(Items.NETHERITE_HOE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.25F));
    }});

    private final Item tool;
    private final Map<DamageType, DamageAttribute> damageSpread;

    DefaultToolDamages(Item itemTool, Map<DamageType, DamageAttribute> damageArray) {
        this.tool = itemTool;
        this.damageSpread = damageArray;
    }

    public Item getTool() {
        return tool;
    }

    public Map<DamageType, DamageAttribute> getDamageSpread() {
        return damageSpread;
    }
}
