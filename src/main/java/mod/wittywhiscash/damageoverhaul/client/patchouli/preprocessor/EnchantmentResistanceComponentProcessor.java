package mod.wittywhiscash.damageoverhaul.client.patchouli.preprocessor;

import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import mod.wittywhiscash.damageoverhaul.api.DamageType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.StringUtils;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EnchantmentResistanceComponentProcessor implements IComponentProcessor {

    private Enchantment enchantment;
    private List<Map.Entry<DamageType, Integer>> attributeList = new LinkedList<>();

    @Override
    public void setup(IVariableProvider iVariableProvider) {
        String enchantmentID = iVariableProvider.get("enchantment").asString();
        Identifier identifier = new Identifier(enchantmentID);
        enchantment = Registry.ENCHANTMENT.getOrEmpty(identifier).orElseThrow(IllegalArgumentException::new);
        for (DamageType type : DamageOverhaul.ATTRIBUTE_DATABASE.getEnchantmentResistanceDatabase().getResistanceSpread(enchantment).keySet()) {
            attributeList.add(new AbstractMap.SimpleEntry<>(type, DamageOverhaul.ATTRIBUTE_DATABASE.getEnchantmentResistanceDatabase().getResistanceSpread(enchantment).get(type)));
        }
    }

    @Override
    public IVariable process(String s) {
        if (s.startsWith("type")) {
            int index = Integer.parseInt(s.substring(4)) - 1;
            if (index >= attributeList.size()) {
                return null;
            }
            DamageType type = attributeList.get(index).getKey();
            return IVariable.wrap(StringUtils.capitalize(type.getRegistryName()));
        }
        else if (s.startsWith("value")) {
            int index = Integer.parseInt(s.substring(5)) - 1;
            if (index >= attributeList.size()) {
                return null;
            }
            Integer value = attributeList.get(index).getValue();
            return value != 1 ? IVariable.wrap(DamageOverhaul.DF.format(value) + " Enchant Units") : IVariable.wrap(DamageOverhaul.DF.format(value) + " Enchant Unit");
        }
        return null;
    }
}
