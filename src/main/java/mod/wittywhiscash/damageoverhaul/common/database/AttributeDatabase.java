package mod.wittywhiscash.damageoverhaul.common.database;

import mod.wittywhiscash.damageoverhaul.common.database.defaults.*;

import java.util.Objects;

public class AttributeDatabase {

    private static AttributeDatabase instance;

    private AttributeDatabase() {}

    public static AttributeDatabase getInstance() {
        if (Objects.isNull(instance)) {
            instance = new AttributeDatabase();
        }
        return instance;
    }

    public void init() {
        for (DefaultDamageTypes damageType : DefaultDamageTypes.values()) {
            getDamageTypeDatabase().registerDamageType(damageType.getRegistryName());
        }

        for (DefaultArmorResistances armorResistance : DefaultArmorResistances.values()) {
            getArmorResistanceDatabase().registerArmorResistance(armorResistance.getArmor(), armorResistance.getModifierSpread());
        }

        for (DefaultDamageSources damageSource : DefaultDamageSources.values()) {
            getDamageSourceDatabase().registerTypedDamageSource(damageSource.getDamageSource(), damageSource.getSpread());
        }

        for (DefaultEntityDamages entityDamage : DefaultEntityDamages.values()) {
            getEntityDamageDatabase().registerEntityDamageSpread(entityDamage.getEntityType(), entityDamage.getDamageSpread());
        }

        for (DefaultEntityResistances entityResistance : DefaultEntityResistances.values()) {
            getEntityResistanceDatabase().registerEntityResistanceSpread(entityResistance.getEntityType(), entityResistance.getProtectionSpread());
        }

        for (DefaultToolDamages toolDamage : DefaultToolDamages.values()) {
            getToolDamageDatabase().registerToolDamageSpread(toolDamage.getTool(), toolDamage.getDamageSpread());
        }

        for (DefaultEnchantmentResistances enchantmentResistance : DefaultEnchantmentResistances.values()) {
            getEnchantmentResistanceDatabase().registerTypedEnchantmentResistance(enchantmentResistance.getEnchantment(), enchantmentResistance.getResistanceSpread());
        }
    }

    public DamageTypes getDamageTypeDatabase() { return DamageTypes.getInstance(); }

    public DamageSources getDamageSourceDatabase() {
        return DamageSources.getInstance();
    }

    public ArmorResistances getArmorResistanceDatabase() { return ArmorResistances.getInstance(); }

    public EntityDamages getEntityDamageDatabase() {
        return EntityDamages.getInstance();
    }

    public EntityResistances getEntityResistanceDatabase() {
        return EntityResistances.getInstance();
    }

    public ToolDamages getToolDamageDatabase() {
        return ToolDamages.getInstance();
    }

    public EnchantmentResistances getEnchantmentResistanceDatabase() {
        return EnchantmentResistances.getInstance();
    }
}
