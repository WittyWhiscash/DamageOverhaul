package mod.wittywhiscash.damageoverhaul.common.database;

import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.damage.DamageAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

import java.util.*;

public class EntityDamages {

    private Map<String, Map<String, DamageAttribute>> entityDamageDatabase = new LinkedHashMap<>();
    private static final Map<EntityType<?>, Map<DamageType, DamageAttribute>> entityDamageDatabase_internal = new LinkedHashMap<>();
    private static final Set<EntityType<?>> entityTypes = new HashSet<>();

    private static EntityDamages instance;

    private EntityDamages() {}

    public EntityDamages(Map<String, Map<String, DamageAttribute>> database) {
        this.entityDamageDatabase = database;
    }

    public static EntityDamages getInstance() {
        if (Objects.isNull(instance)) {
            instance = new EntityDamages();
        }
        return instance;
    }

    public Map<String, Map<String, DamageAttribute>> getEntityDamageDatabase() {
        return entityDamageDatabase;
    }
    public void setEntityDamageDatabase(Map<String, Map<String, DamageAttribute>> entityDamageDatabase) { this.entityDamageDatabase = entityDamageDatabase; }

    public void registerEntityDamageSpread(EntityType<?> type, Map<DamageType, DamageAttribute> damageSpread) {
        if (!entityDamageDatabase_internal.containsKey(type)) {
            Map<String, DamageAttribute> jsonParsedDamageSpread = new HashMap<>();
            for (Map.Entry<DamageType, DamageAttribute> entry : damageSpread.entrySet()) {
                jsonParsedDamageSpread.put(entry.getKey().getRegistryName(), entry.getValue());
            }
            entityDamageDatabase_internal.put(type, damageSpread);
            entityDamageDatabase.put(Registry.ENTITY_TYPE.getId(type).toString(), jsonParsedDamageSpread);
        }
        entityTypes.add(type);
    }

    public Map<DamageType, DamageAttribute> getDamageSpread(EntityType<?> type) {
        return entityDamageDatabase_internal.get(type);
    }

    public static Set<EntityType<?>> values() { return entityTypes; }

    public boolean contains(EntityType<?> type) { return entityTypes.contains(type); }
}
