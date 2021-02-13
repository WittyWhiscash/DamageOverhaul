package mod.wittywhiscash.damageoverhaul.client.patchouli.preprocessor;

import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import mod.wittywhiscash.damageoverhaul.api.DamageCondition;
import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.damage.DamageAttribute;
import net.minecraft.client.resource.language.I18n;
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

public class EntityDamageComponentProcessor implements IComponentProcessor {

    private EntityType<?> entityType = null;
    private String translationKey = null;
    private List<Map.Entry<DamageType, DamageAttribute>> attributeList = new LinkedList<>();
    DecimalFormat df = new DecimalFormat("###");

    @Override
    public void setup(IVariableProvider iVariableProvider) {
        String entityID = iVariableProvider.get("entity").asString();
        Identifier identifier = new Identifier(entityID);
        entityType = Registry.ENTITY_TYPE.getOrEmpty(identifier).orElseThrow(IllegalArgumentException::new);
        translationKey = entityType.getTranslationKey();
        for (DamageType type : DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).keySet()) {
            attributeList.add(new AbstractMap.SimpleEntry<>(type, DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).get(type)));
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
        else if (s.startsWith("title")) {
            String translatedEntityName = I18n.translate(translationKey);
            return IVariable.wrap(translatedEntityName + " - Damage Spread");
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
