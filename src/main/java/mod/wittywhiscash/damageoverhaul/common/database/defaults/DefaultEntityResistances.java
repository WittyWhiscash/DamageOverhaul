package mod.wittywhiscash.damageoverhaul.common.database.defaults;

import mod.wittywhiscash.damageoverhaul.api.DamageCondition;
import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.damage.DamageAttribute;
import mod.wittywhiscash.damageoverhaul.common.database.DamageTypes;
import net.minecraft.entity.EntityType;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public enum DefaultEntityResistances {
    BAT(EntityType.BAT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    BEE(EntityType.BEE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.BEE);
    }}),

    BLAZE(EntityType.BLAZE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.BLAZE);
        add(EntityType.SMALL_FIREBALL);
    }}),

    CAT(EntityType.CAT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.CAT);
    }}),

    CAVE_SPIDER(EntityType.CAVE_SPIDER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.WEAK, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.CAVE_SPIDER);
    }}),

    CHICKEN(EntityType.CHICKEN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    COD(EntityType.COD, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    COW(EntityType.COW, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    CREEPER(EntityType.CREEPER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.WEAK, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.CREEPER);
    }}),

    DOLPHIN(EntityType.DOLPHIN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.DOLPHIN);
    }}),

    DONKEY(EntityType.DONKEY, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    DROWNED(EntityType.DROWNED, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.DROWNED);
        add(EntityType.TRIDENT);
    }}),

    ELDER_GUARDIAN(EntityType.ELDER_GUARDIAN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.ELDER_GUARDIAN);
    }}),

    ENDER_DRAGON(EntityType.ENDER_DRAGON, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.ENDER_DRAGON);
        add(EntityType.AREA_EFFECT_CLOUD);
    }}),

    ENDERMAN(EntityType.ENDERMAN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.ENDERMAN);
    }}),

    ENDERMITE(EntityType.ENDERMITE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.WEAK, 0.5F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.ENDERMITE);
    }}),

    EVOKER(EntityType.EVOKER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.EVOKER_FANGS);
    }}),

    FOX(EntityType.FOX, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.FOX);
    }}),

    GHAST(EntityType.GHAST, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.75F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.WEAK, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.FIREBALL);
    }}),

    GIANT(EntityType.GIANT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.WEAK, 0.25F));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.GIANT);
    }}),

    GUARDIAN(EntityType.GUARDIAN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.GUARDIAN);
    }}),

    HOGLIN(EntityType.HOGLIN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.HOGLIN);
    }}),

    HORSE(EntityType.HORSE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    HUSK(EntityType.HUSK, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.HUSK);
    }}),

    ILLUSIONER(EntityType.ILLUSIONER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.ARROW);
    }}),

    IRON_GOLEM(EntityType.IRON_GOLEM, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.RESISTANT, 0.75F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.75F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.IRON_GOLEM);
    }}),

    LLAMA(EntityType.LLAMA, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.LLAMA_SPIT);
    }}),

    MAGMA_CUBE(EntityType.MAGMA_CUBE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.MAGMA_CUBE);
    }}),

    MULE(EntityType.MULE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    MOOSHROOM(EntityType.MOOSHROOM, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    OCELOT(EntityType.OCELOT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.OCELOT);
    }}),

    PANDA(EntityType.PANDA, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.PANDA);
    }}),

    PARROT(EntityType.PARROT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.WEAK, 0.5F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    PHANTOM(EntityType.PHANTOM, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.PHANTOM);
    }}),

    PIG(EntityType.PIG, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    PIGLIN(EntityType.PIGLIN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.PIGLIN);
    }}),

    PIGLIN_BRUTE(EntityType.PIGLIN_BRUTE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.PIGLIN_BRUTE);
    }}),

    PILLAGER(EntityType.PILLAGER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.ARROW);
    }}),

    PLAYER(EntityType.PLAYER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.PLAYER);
    }}),

    POLAR_BEAR(EntityType.POLAR_BEAR, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.POLAR_BEAR);
    }}),

    PUFFERFISH(EntityType.PUFFERFISH, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.PUFFERFISH);
    }}),

    RABBIT(EntityType.RABBIT, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    RAVAGER(EntityType.RAVAGER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.RAVAGER);
    }}),

    SALMON(EntityType.SALMON, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    SHEEP(EntityType.SHEEP, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    SHULKER(EntityType.SHULKER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.SHULKER_BULLET);
    }}),

    SILVERFISH(EntityType.SILVERFISH, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.SILVERFISH);
    }}),

    SKELETON(EntityType.SKELETON, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.75F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.SKELETON);
        add(EntityType.ARROW);
    }}),

    SKELETON_HORSE(EntityType.SKELETON_HORSE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.75F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<>()),

    SLIME(EntityType.SLIME, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.SLIME);
    }}),

    SNOW_GOLEM(EntityType.SNOW_GOLEM, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.75F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.SNOWBALL);
    }}),

    SPIDER(EntityType.SPIDER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.WEAK, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.SPIDER);
    }}),

    SQUID(EntityType.SQUID, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    STRAY(EntityType.STRAY, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.75F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.STRAY);
        add(EntityType.ARROW);
    }}),

    STRIDER(EntityType.STRIDER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    TRADER_LLAMA(EntityType.TRADER_LLAMA, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.LLAMA_SPIT);
    }}),

    TROPICAL_FISH(EntityType.TROPICAL_FISH, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    TURTLE(EntityType.TURTLE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    VEX(EntityType.VEX, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.VEX);
    }}),

    VILLAGER(EntityType.VILLAGER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    VINDICATOR(EntityType.VINDICATOR, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.VINDICATOR);
    }}),

    WANDERING_TRADER(EntityType.WANDERING_TRADER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<>()),

    WITCH(EntityType.WITCH, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.WEAK, 0.5F));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.POTION);
        add(EntityType.AREA_EFFECT_CLOUD);
    }}),

    WITHER(EntityType.WITHER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.RESISTANT, 0.5F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.WITHER_SKULL);
    }}),

    WITHER_SKELETON(EntityType.WITHER_SKELETON, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.RESISTANT, 0.75F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.WITHER_SKELETON);
    }}),

    WOLF(EntityType.WOLF, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.NEUTRAL));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.WOLF);
    }}),

    ZOGLIN(EntityType.ZOGLIN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.WEAK, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.ZOGLIN);
    }}),

    ZOMBIE(EntityType.ZOMBIE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.ZOMBIE);
    }}),

    ZOMBIE_HORSE(EntityType.ZOMBIE_HORSE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<>()),

    ZOMBIE_VILLAGER(EntityType.ZOMBIE_VILLAGER, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.VULNERABLE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.ZOMBIE_VILLAGER);
    }}),

    ZOMBIFIED_PIGLIN(EntityType.ZOMBIFIED_PIGLIN, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(DamageCondition.RESISTANT, 0.25F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(DamageCondition.NEUTRAL));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(DamageCondition.IMMUNE));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(DamageCondition.IMMUNE));
    }}, new LinkedHashSet<EntityType<?>>(){{
        add(EntityType.ZOMBIFIED_PIGLIN);
    }});

    private final EntityType<?> entityType;
    private final Map<DamageType, DamageAttribute> protectionSpread;
    private final Set<EntityType<?>> damageAssociations;

    DefaultEntityResistances(EntityType<?> entity, Map<DamageType, DamageAttribute> protectionArray, Set<EntityType<?>> associations) {
        this.entityType = entity;
        this.protectionSpread = protectionArray;
        this.damageAssociations = associations;
    }

    public EntityType<?> getEntityType() { return entityType; }
    public Map<DamageType, DamageAttribute> getProtectionSpread() { return protectionSpread; }
    public Set<EntityType<?>> getDamageAssociations() { return damageAssociations; }
}
