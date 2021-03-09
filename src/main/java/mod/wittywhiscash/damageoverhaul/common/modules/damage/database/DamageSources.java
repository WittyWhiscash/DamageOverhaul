package mod.wittywhiscash.damageoverhaul.common.modules.damage.database;

import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.util.DamageAttribute;
import net.minecraft.entity.damage.DamageSource;

import java.util.*;

public class DamageSources {


    private Map<String, Map<String, DamageAttribute>> damageSourceDatabase = new LinkedHashMap<>();
    private static final Map<DamageSource, Map<DamageType, DamageAttribute>> damageSourceDatabase_internal = new LinkedHashMap<>();
    private static final Set<DamageSource> damageSources = new HashSet<>();

    private static DamageSources instance;

    private DamageSources() {}

    public DamageSources(Map<String, Map<String, DamageAttribute>> database) {
        damageSourceDatabase = database;
    }

    public static DamageSources getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DamageSources();
        }
        return instance;
    }

    public Map<String, Map<String, DamageAttribute>> getDamageSourceDatabase() { return damageSourceDatabase; }
    public void setDamageSourceDatabase(Map<String, Map<String, DamageAttribute>> damageSourceDatabase) { this.damageSourceDatabase = damageSourceDatabase; }

    public void registerTypedDamageSource(DamageSource source, Map<DamageType, DamageAttribute> damageSpread) {
        if (!damageSourceDatabase_internal.containsKey(source)) {
            Map<String, DamageAttribute> jsonParsedDamageSpread = new HashMap<>();
            for (Map.Entry<DamageType, DamageAttribute> entry : damageSpread.entrySet()) {
                jsonParsedDamageSpread.put(entry.getKey().getRegistryName(), entry.getValue());
            }
            damageSourceDatabase_internal.put(source, damageSpread);
            damageSourceDatabase.put(source.getName(), jsonParsedDamageSpread);
            damageSources.add(source);
        }
    }

    public Map<DamageType, DamageAttribute> getDamageSpread(DamageSource source) { return damageSourceDatabase_internal.get(source); }

    public boolean contains(DamageSource source) { return damageSources.contains(source); }

    public static DamageSource[] values() { return damageSources.toArray(new DamageSource[0]); }
}
