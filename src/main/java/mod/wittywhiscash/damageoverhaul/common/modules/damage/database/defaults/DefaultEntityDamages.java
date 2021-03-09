package mod.wittywhiscash.damageoverhaul.common.modules.damage.database.defaults;

import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.util.DamageAttribute;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.database.DamageTypes;
import net.minecraft.entity.EntityType;

import java.util.*;

public enum DefaultEntityDamages {
    AREA_EFFECT_CLOUD(EntityType.AREA_EFFECT_CLOUD, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(1.0F));
    }}),
    ARROW(EntityType.ARROW, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.2F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.8F));
    }}),
    BEE(EntityType.BEE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    BLAZE(EntityType.BLAZE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(1.0F));
    }}),
    CAT(EntityType.CAT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
    }}),
    CAVE_SPIDER(EntityType.CAVE_SPIDER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.5F));
    }}),
    CREEPER(EntityType.CREEPER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(0.25F));
    }}),
    DOLPHIN(EntityType.DOLPHIN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    DROWNED(EntityType.DROWNED, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(0.25F));
    }}),
    EGG(EntityType.EGG, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(1.0F));
    }}),
    ENDER_DRAGON(EntityType.ENDER_DRAGON, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
    }}),
    ENDER_PEARL(EntityType.ENDER_PEARL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    ENDERMAN(EntityType.ENDERMAN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.5F));
    }}),
    ENDERMITE(EntityType.ENDERMITE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.5F));
    }}),
    EVOKER_FANGS(EntityType.EVOKER_FANGS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.5F));
    }}),
    FIREBALL(EntityType.FIREBALL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(0.5F));
    }}),
    FIREWORK_ROCKET(EntityType.FIREWORK_ROCKET, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
    }}),
    FOX(EntityType.FOX, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    GIANT(EntityType.GIANT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    GUARDIAN(EntityType.GUARDIAN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(1.0F));
    }}),
    HOGLIN(EntityType.HOGLIN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));;
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
    }}),
    HUSK(EntityType.HUSK, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(0.25F));
    }}),
    IRON_GOLEM(EntityType.IRON_GOLEM, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    LLAMA_SPIT(EntityType.LLAMA_SPIT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.5F));
    }}),
    MAGMA_CUBE(EntityType.MAGMA_CUBE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(0.5F));
    }}),
    TNT_MINECART(EntityType.TNT_MINECART, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(0.25F));
    }}),
    OCELOT(EntityType.OCELOT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    PANDA(EntityType.PANDA, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
    }}),
    PHANTOM(EntityType.PHANTOM, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.5F));
    }}),
    PIGLIN(EntityType.PIGLIN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(1.0F));
    }}),
    PIGLIN_BRUTE(EntityType.PIGLIN_BRUTE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    PLAYER(EntityType.PLAYER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    POLAR_BEAR(EntityType.POLAR_BEAR, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
    }}),
    POTION(EntityType.POTION, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(1.0F));
    }}),
    PUFFERFISH(EntityType.PUFFERFISH, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.5F));
    }}),
    RAVAGER(EntityType.RAVAGER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
    }}),
    SHULKER_BULLET(EntityType.SHULKER_BULLET, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(1.0F));
    }}),
    SILVERFISH(EntityType.SILVERFISH, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    SKELETON(EntityType.SKELETON, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    SLIME(EntityType.SLIME, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    SMALL_FIREBALL(EntityType.SMALL_FIREBALL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(0.5F));
    }}),
    SNOWBALL(EntityType.SNOWBALL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.5F));
    }}),
    SPECTRAL_ARROW(EntityType.SPECTRAL_ARROW, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(0.5F));
    }}),
    SPIDER(EntityType.SPIDER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    STRAY(EntityType.STRAY, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    TRIDENT(EntityType.TRIDENT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.25F));
    }}),
    VEX(EntityType.VEX, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(1.0F));
    }}),
    VINDICATOR(EntityType.VINDICATOR, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
    }}),
    WITHER_SKELETON(EntityType.WITHER_SKELETON, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(0.5F));
    }}),
    WITHER_SKULL(EntityType.WITHER_SKULL, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(0.75F));
    }}),
    WOLF(EntityType.WOLF, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    ZOGLIN(EntityType.ZOGLIN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(0.25F));
    }}),
    ZOMBIE(EntityType.ZOMBIE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(0.25F));
    }}),
    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(0.25F));
    }}),
    ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(0.75F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(0.25F));
    }});

    private final EntityType<?> entityType;
    private final Map<DamageType, DamageAttribute> damageSpread;

    DefaultEntityDamages(EntityType<?> type, Map<DamageType, DamageAttribute> damageArray) {
        this.entityType = type;
        this.damageSpread = damageArray;
    }

    public EntityType<?> getEntityType() {
        return entityType;
    }

    public Map<DamageType, DamageAttribute> getDamageSpread() {
        return damageSpread;
    }
}
