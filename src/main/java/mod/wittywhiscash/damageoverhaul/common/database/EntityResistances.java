package mod.wittywhiscash.damageoverhaul.common.database;

import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.damage.DamageAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

import java.util.*;

public class EntityResistances {

    private Map<String, Map<String, DamageAttribute>> entityResistanceDatabase = new LinkedHashMap<>();
    private static final Map<EntityType<?>, Map<DamageType, DamageAttribute>> entityResistanceDatabase_internal = new LinkedHashMap<>();
    private static final Set<EntityType<?>> entityTypes = new HashSet<>();

    private static EntityResistances instance;

    private EntityResistances() { }

    public Map<String, Map<String, DamageAttribute>> getEntityResistanceDatabase() { return entityResistanceDatabase; }
    public void setEntityResistanceDatabase(Map<String, Map<String, DamageAttribute>> entityResistanceDatabase) { this.entityResistanceDatabase = entityResistanceDatabase; }

    public static EntityResistances getInstance() {
        if (Objects.isNull(instance)) {
            instance = new EntityResistances();
        }
        return instance;
    }

    public void registerEntityResistanceSpread(EntityType<?> type, Map<DamageType, DamageAttribute> resistanceSpread) {
        if (!entityResistanceDatabase_internal.containsKey(type)) {
            Map<String, DamageAttribute> jsonParsedResistanceSpread = new HashMap<>();
            for (Map.Entry<DamageType, DamageAttribute> entry : resistanceSpread.entrySet()) {
                jsonParsedResistanceSpread.put(entry.getKey().getRegistryName(), entry.getValue());
            }
            entityResistanceDatabase_internal.put(type, resistanceSpread);
            entityResistanceDatabase.put(Registry.ENTITY_TYPE.getId(type).toString(), jsonParsedResistanceSpread);
        }
        entityTypes.add(type);
    }

    public Map<DamageType, DamageAttribute> getResistanceSpread(EntityType<?> type) {
        return entityResistanceDatabase_internal.get(type);
    }

    public static Set<EntityType<?>> values() { return entityTypes; }

    public boolean contains(EntityType<?> type) { return entityTypes.contains(type); }

}
