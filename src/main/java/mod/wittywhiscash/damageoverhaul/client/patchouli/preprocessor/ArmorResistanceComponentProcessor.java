package mod.wittywhiscash.damageoverhaul.client.patchouli.preprocessor;

import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.util.DamageAttribute;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.item.Item;
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

public class ArmorResistanceComponentProcessor implements IComponentProcessor {
    private Item armorItem = null;
    private List<Map.Entry<DamageType, DamageAttribute>> attributeList = new LinkedList<>();

    @Override
    public void setup(IVariableProvider iVariableProvider) {
        String toolID = iVariableProvider.get("item").asString();
        Identifier identifier = new Identifier(toolID);
        armorItem = Registry.ITEM.getOrEmpty(identifier).orElseThrow(IllegalArgumentException::new);
        for (DamageType type : DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().getResistanceSpread(armorItem).keySet()) {
            attributeList.add(new AbstractMap.SimpleEntry<>(type, DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().getResistanceSpread(armorItem).get(type)));
        }
    }

    @Override
    public IVariable process(String s) {
        if (s.startsWith("item")) {
            return IVariable.wrap(Registry.ITEM.getId(armorItem).toString());
        }
        else if (s.startsWith("title")) {
            return IVariable.wrap(I18n.translate(armorItem.getTranslationKey()));
        }
        else if (s.startsWith("type")) {
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
            Float value = attributeList.get(index).getValue().getModifier();
            return value > 1 || value < 1 ? IVariable.wrap(DamageOverhaul.DF.format(value) + " Armor Units") : IVariable.wrap(DamageOverhaul.DF.format(value) + " Armor Unit");
        }
        return null;
    }
}
