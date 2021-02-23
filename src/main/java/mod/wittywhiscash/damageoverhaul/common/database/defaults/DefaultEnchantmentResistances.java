package mod.wittywhiscash.damageoverhaul.common.database.defaults;

import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.database.DamageTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;

import java.util.LinkedHashMap;
import java.util.Map;

public enum DefaultEnchantmentResistances {

    BLAST_PROTECTION(Enchantments.BLAST_PROTECTION, new LinkedHashMap<DamageType, Integer>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), 2);
    }}),
    FEATHER_FALLING(Enchantments.FEATHER_FALLING, new LinkedHashMap<DamageType, Integer>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), 2);
        put(DamageTypes.valueOf(DamageType.PIERCING), 1);
    }}),
    FIRE_PROTECTION(Enchantments.FIRE_PROTECTION, new LinkedHashMap<DamageType, Integer>(){{
        put(DamageTypes.valueOf(DamageType.MAGIC), 2);
    }}),
    PROTECTION(Enchantments.PROTECTION, new LinkedHashMap<DamageType, Integer>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), 1);
        put(DamageTypes.valueOf(DamageType.SLASHING), 1);
        put(DamageTypes.valueOf(DamageType.PIERCING), 1);
    }}),
    PROJECTILE_PROTECTION(Enchantments.PROJECTILE_PROTECTION, new LinkedHashMap<DamageType, Integer>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), 2);
        put(DamageTypes.valueOf(DamageType.SLASHING), 2);
    }});

    private final Enchantment enchantment;
    private final Map<DamageType, Integer> resistanceSpread;

    DefaultEnchantmentResistances(Enchantment enchantment, Map<DamageType, Integer> resistanceArray) {
        this.enchantment = enchantment;
        this.resistanceSpread = resistanceArray;
    }

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public Map<DamageType, Integer> getResistanceSpread() {
        return resistanceSpread;
    }
}
