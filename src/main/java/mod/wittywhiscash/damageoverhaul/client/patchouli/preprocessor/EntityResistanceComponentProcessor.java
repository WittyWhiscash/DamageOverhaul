package mod.wittywhiscash.damageoverhaul.client.patchouli.preprocessor;

import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import mod.wittywhiscash.damageoverhaul.api.DamageCondition;
import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.util.DamageAttribute;
import net.minecraft.entity.EntityType;
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

public class EntityResistanceComponentProcessor implements IComponentProcessor {

    private EntityType<?> entityType = null;
    private List<Map.Entry<DamageType, DamageAttribute>> attributeList = new LinkedList<>();
    DecimalFormat df = new DecimalFormat("###");

    @Override
    public void setup(IVariableProvider iVariableProvider) {
        String entityID = iVariableProvider.get("entity").asString();
        Identifier identifier = new Identifier(entityID);
        entityType = Registry.ENTITY_TYPE.getOrEmpty(identifier).orElseThrow(IllegalArgumentException::new);
        for (DamageType type : DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().getResistanceSpread(entityType).keySet()) {
            attributeList.add(new AbstractMap.SimpleEntry<>(type, DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().getResistanceSpread(entityType).get(type)));
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
        else if (s.startsWith("condition")) {
            int index = Integer.parseInt(s.substring(9)) - 1;
            if (index >= attributeList.size()) {
                return null;
            }
            DamageCondition condition = attributeList.get(index).getValue().getDamageCondition();
            return IVariable.wrap(condition.toString());
        }
        else if (s.startsWith("value")) {
            int index = Integer.parseInt(s.substring(5)) - 1;
            if (index >= attributeList.size()) {
                return null;
            }
            DamageCondition condition = attributeList.get(index).getValue().getDamageCondition();
            Float value = attributeList.get(index).getValue().getModifier();
            switch (condition) {
                case VULNERABLE:
                    return DamageOverhaul.CONFIG.DEBUG.getColorblindMode() ? IVariable.wrap("$(d)200%%") : IVariable.wrap("$(2)200%%");
                case WEAK:
                    return DamageOverhaul.CONFIG.DEBUG.getColorblindMode() ? IVariable.wrap("$(d)" + DamageOverhaul.DF.format((value + 1) * 100) + "%%") : IVariable.wrap("$(2)" + DamageOverhaul.DF.format((value + 1) * 100) + "%%");
                case RESISTANT:
                    return DamageOverhaul.CONFIG.DEBUG.getColorblindMode() ? IVariable.wrap("$(1)" + DamageOverhaul.DF.format((1 - value) * 100) + "%%") : IVariable.wrap("$(c)" + DamageOverhaul.DF.format((1 - value) * 100) + "%%");
                case IMMUNE:
                    return DamageOverhaul.CONFIG.DEBUG.getColorblindMode() ? IVariable.wrap("$(1)0%%") : IVariable.wrap("$(c)0%%");
                default:
                    return IVariable.wrap("100%%");
            }
        }
        return null;
    }
}
