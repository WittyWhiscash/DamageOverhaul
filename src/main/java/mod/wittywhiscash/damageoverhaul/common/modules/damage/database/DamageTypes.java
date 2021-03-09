package mod.wittywhiscash.damageoverhaul.common.modules.damage.database;

import mod.wittywhiscash.damageoverhaul.api.DamageType;

import java.util.*;

public class DamageTypes {

    private Map<String, String> damageTypeRegistry = new LinkedHashMap<>();
    private static final Map<String, DamageType> damageTypeRegistry_internal = new LinkedHashMap<>();
    private static final Set<DamageType> damageTypes = new HashSet<>();

    private static DamageTypes instance;

    private DamageTypes() {

    }

    public Map<String, String> getDamageTypeRegistry() { return damageTypeRegistry; }
    public void setDamageTypeRegistry(Map<String, String> registry) { this.damageTypeRegistry = registry; }

    public static DamageTypes getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DamageTypes();
        }
        return instance;
    }

    public void registerDamageType(String registryName) {
        DamageType newType = new DamageType(registryName);
        if (!damageTypeRegistry_internal.containsKey(registryName)) {
            damageTypeRegistry_internal.put(registryName.toUpperCase(), newType);
            damageTypeRegistry.put(registryName.toUpperCase(), newType.getRegistryName());
        }
        damageTypes.add(newType);
    }

    public static DamageType valueOf(String registryName) {
        return damageTypeRegistry_internal.get(registryName.toUpperCase());
    }

    public static DamageType[] values() { return damageTypes.toArray(new DamageType[0]);}

    public boolean contains(String registryName) { return damageTypeRegistry.containsValue(registryName); }

}
