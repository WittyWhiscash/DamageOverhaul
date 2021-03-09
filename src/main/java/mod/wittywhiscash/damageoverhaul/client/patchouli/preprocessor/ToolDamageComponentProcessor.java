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

import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ToolDamageComponentProcessor implements IComponentProcessor {

    private Item toolItem = null;
    private List<Map.Entry<DamageType, DamageAttribute>> attributeList = new LinkedList<>();
    private DecimalFormat df = new DecimalFormat("###");

    @Override
    public void setup(IVariableProvider iVariableProvider) {
        String toolID = iVariableProvider.get("item").asString();
        Identifier identifier = new Identifier(toolID);
        toolItem = Registry.ITEM.getOrEmpty(identifier).orElseThrow(IllegalArgumentException::new);
        for (DamageType type : DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).keySet()) {
            attributeList.add(new AbstractMap.SimpleEntry<>(type, DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).get(type)));
        }
    }

    @Override
    public IVariable process(String s) {
        if (s.startsWith("item")) {
            return IVariable.wrap(Registry.ITEM.getId(toolItem).toString());
        }
        else if (s.startsWith("title")) {
            return IVariable.wrap(I18n.translate(toolItem.getTranslationKey()));
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
            return IVariable.wrap(DamageOverhaul.DF.format((value) * 100) + "%%");
        }
        return null;
    }
}
