package mod.wittywhiscash.damageoverhaul.common.database;

import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.damage.DamageAttribute;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

import java.util.*;

public class ArmorResistances {

    private Map<String, Map<String, DamageAttribute>> armorResistanceDatabase = new LinkedHashMap<>();
    private static final Map<Item, Map<DamageType, DamageAttribute>> armorResistanceDatabase_internal = new LinkedHashMap<>();
    private static final Set<Item> armorItems = new HashSet<>();

    private static ArmorResistances instance;

    private ArmorResistances() {}

    public Map<String, Map<String, DamageAttribute>> getArmorResistanceDatabase() { return armorResistanceDatabase; }
    public void setArmorResistanceDatabase(Map<String, Map<String, DamageAttribute>> database) { this.armorResistanceDatabase = database; }

    public static ArmorResistances getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ArmorResistances();
        }
        return instance;
    }

    public void registerArmorResistance(Item armorItem, Map<DamageType, DamageAttribute> resistanceSpread) {
        if (!armorResistanceDatabase_internal.containsKey(armorItem)) {
            Map<String, DamageAttribute> jsonParsedResistanceSpread = new HashMap<>();
            for (Map.Entry<DamageType, DamageAttribute> entry : resistanceSpread.entrySet()) {
                jsonParsedResistanceSpread.put(entry.getKey().getRegistryName(), entry.getValue());
            }
            armorResistanceDatabase_internal.put(armorItem, resistanceSpread);
            armorResistanceDatabase.put(Registry.ITEM.getId(armorItem).toString(), jsonParsedResistanceSpread);
        }
        armorItems.add(armorItem);
    }

    public Map<DamageType, DamageAttribute> getResistanceSpread(Item armorItem) {
        return armorResistanceDatabase_internal.get(armorItem);
    }

    public static Set<Item> values() { return armorItems; }

    public boolean contains(Item armorItem) { return armorItems.contains(armorItem); }

}
