package mod.wittywhiscash.damageoverhaul.common.database;

import mod.wittywhiscash.damageoverhaul.api.DamageType;
import net.minecraft.enchantment.Enchantment;

import java.util.*;

public class EnchantmentResistances {

    private final Map<String, Map<String, Integer>> enchantmentEffectivenessDatabase = new HashMap<>();
    private static final Map<Enchantment, Map<DamageType, Integer>> enchantmentEffectivenessDatabase_internal = new HashMap<>();
    private static final Set<Enchantment> enchantments = new HashSet<>();

    private static EnchantmentResistances instance;

    private EnchantmentResistances() {}

    public Map<String, Map<String, Integer>> getEnchantmentEffectivenessDatabase() {
        return enchantmentEffectivenessDatabase;
    }

    public static EnchantmentResistances getInstance() {
        if (Objects.isNull(instance)) {
            instance = new EnchantmentResistances();
        }
        return instance;
    }

    public void registerTypedEnchantmentResistance(Enchantment enchantment, Map<DamageType, Integer> resistanceSpread) {
        if (!enchantmentEffectivenessDatabase_internal.containsKey(enchantment)) {
            Map<String, Integer> jsonParsedResistanceSpread = new HashMap<>();
            for (Map.Entry<DamageType, Integer> entry : resistanceSpread.entrySet()) {
                jsonParsedResistanceSpread.put(entry.getKey().getRegistryName(), entry.getValue());
            }
            enchantmentEffectivenessDatabase_internal.put(enchantment, resistanceSpread);
            enchantmentEffectivenessDatabase.put(enchantment.getTranslationKey(), jsonParsedResistanceSpread);
            enchantments.add(enchantment);
        }
    }

    public Map<DamageType, Integer> getResistanceSpread(Enchantment enchantment) { return enchantmentEffectivenessDatabase_internal.get(enchantment); }

    public boolean contains(Enchantment enchantment) { return enchantments.contains(enchantment); }

    public static Enchantment[] values() { return enchantments.toArray(new Enchantment[0]); }
}
