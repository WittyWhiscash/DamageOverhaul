package mod.wittywhiscash.damageoverhaul.common.database;

import com.google.gson.annotations.Expose;
import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.damage.DamageAttribute;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import java.util.*;

public class ToolDamages {

    private Map<String, Map<String, DamageAttribute>> toolDamageDatabase = new LinkedHashMap<>();
    private static final Map<Item, Map<DamageType, DamageAttribute>> toolDamageDatabase_internal = new LinkedHashMap<>();
    private static final Set<Item> toolItems = new HashSet<>();

    private static ToolDamages instance;

    private ToolDamages() { }

    public ToolDamages(Map<String, Map<String, DamageAttribute>> database) {
        this.toolDamageDatabase = database;
    }

    public Map<String, Map<String, DamageAttribute>> getToolDamageDatabase() { return toolDamageDatabase; }
    public void setToolDamageDatabase(Map<String, Map<String, DamageAttribute>> toolDamageDatabase) { this.toolDamageDatabase = toolDamageDatabase; }

    public static ToolDamages getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ToolDamages();
        }
        return instance;
    }

    public void registerToolDamageSpread(Item toolItem, Map<DamageType, DamageAttribute> damageSpread) {
        if (!toolDamageDatabase_internal.containsKey(toolItem)) {
            Map<String, DamageAttribute> jsonParsedDamageSpread = new HashMap<>();
            for (Map.Entry<DamageType, DamageAttribute> entry : damageSpread.entrySet()) {
                jsonParsedDamageSpread.put(entry.getKey().getRegistryName(), entry.getValue());
            }
            toolDamageDatabase_internal.put(toolItem, damageSpread);
            toolDamageDatabase.put(Registry.ITEM.getId(toolItem).toString(), jsonParsedDamageSpread);
        }
        toolItems.add(toolItem);
    }

    public Map<DamageType, DamageAttribute> getDamageSpread(Item toolItem) {
        return toolDamageDatabase_internal.get(toolItem);
    }

    public static Set<Item> values() { return toolItems; }

    public boolean contains(Item toolItem) { return toolItems.contains(toolItem); }

}
