package mod.wittywhiscash.damageoverhaul.common.modules.damage.database.defaults;

import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.util.DamageAttribute;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.database.DamageTypes;
import net.minecraft.entity.damage.DamageSource;

import java.util.LinkedHashMap;
import java.util.Map;

public enum DefaultDamageSources {
    ANVIL(DamageSource.ANVIL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    CACTUS(DamageSource.CACTUS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    CRAMMING(DamageSource.CRAMMING, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    DRAGON_BREATH(DamageSource.DRAGON_BREATH, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.5F));
    }}),
    DROWN(DamageSource.DROWN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(1.0F));
    }}),
    DRYOUT(DamageSource.DRYOUT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    FALL(DamageSource.FALL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.25F));
    }}),
    FALLING_BLOCK(DamageSource.FALLING_BLOCK, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    FLY_INTO_WALL(DamageSource.FLY_INTO_WALL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.25F));
    }}),
    GENERIC(DamageSource.GENERIC, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    HOT_FLOOR(DamageSource.HOT_FLOOR, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(1.0F));
    }}),
    IN_FIRE(DamageSource.IN_FIRE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(1.0F));
    }}),
    IN_WALL(DamageSource.IN_WALL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
    }}),
    LAVA(DamageSource.LAVA, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(1.0F));
    }}),
    LIGHTNING_BOLT(DamageSource.LIGHTNING_BOLT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(0.25F));
    }}),
    MAGIC(DamageSource.MAGIC, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(1.0F));
    }}),
    ON_FIRE(DamageSource.ON_FIRE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(1.0F));
    }}),
    OUT_OF_WORLD(DamageSource.OUT_OF_WORLD, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(1.0F));
    }}),
    STARVE(DamageSource.STARVE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(1.0F));
    }}),
    SWEET_BERRY_BUSH(DamageSource.SWEET_BERRY_BUSH, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
    }}),
    WITHER(DamageSource.WITHER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(1.0F));
    }});

    private final DamageSource damageSource;
    private final Map<DamageType, DamageAttribute> damageSpread;

    DefaultDamageSources(DamageSource source, Map<DamageType, DamageAttribute> damageArray) {
        this.damageSource = source;
        this.damageSpread = damageArray;
    }

    public DamageSource getDamageSource() {
        return damageSource;
    }

    public Map<DamageType, DamageAttribute> getSpread() {
        return damageSpread;
    }
}
