package mod.wittywhiscash.damageoverhaul.common.database.defaults;

import mod.wittywhiscash.damageoverhaul.api.DamageType;

public enum DefaultDamageTypes {

    /*
        # So there really are not a lot of things that deal poison damage,
        # and as such I saw fit to merge it into wither. This also gives 6
        # damage types, which is the first perfect number, so there's that
        -Csillagvihar
    */

    BLUDGEONING(DamageType.BLUDGEONING),
    SLASHING(DamageType.SLASHING),
    PIERCING(DamageType.PIERCING),
    MAGIC(DamageType.MAGIC),
    FIRE(DamageType.FIRE),
    WITHER(DamageType.WITHER);

    DefaultDamageTypes(String type) {
        this.registryName = type.toLowerCase();
    }

    private final String registryName;

    public String getRegistryName() {
        return registryName;
    }
}
