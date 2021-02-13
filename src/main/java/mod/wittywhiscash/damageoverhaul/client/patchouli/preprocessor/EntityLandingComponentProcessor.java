package mod.wittywhiscash.damageoverhaul.client.patchouli.preprocessor;

import mod.wittywhiscash.damageoverhaul.client.patchouli.EntityDescription;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class EntityLandingComponentProcessor implements IComponentProcessor {

    private EntityType<?> entityType = null;
    private String blockSummary = null;
    private String blockDetails = null;

    @Override
    public void setup(IVariableProvider iVariableProvider) {
        String entityID = iVariableProvider.get("entity").asString();
        Identifier identifier = new Identifier(entityID);
        entityType = Registry.ENTITY_TYPE.getOrEmpty(identifier).orElseThrow(IllegalArgumentException::new);
        blockSummary = EntityDescription.valueOf(Registry.ENTITY_TYPE.getId(entityType).getPath().toUpperCase()).getSummaryBlock();
        blockDetails = EntityDescription.valueOf(Registry.ENTITY_TYPE.getId(entityType).getPath().toUpperCase()).getDescriptionBlock();
    }

    @Override
    public IVariable process(String s) {
        if (s.startsWith("title")) {
            return IVariable.wrap(I18n.translate(entityType.getTranslationKey()));
        }
        if (s.startsWith("entity")) {
            return IVariable.wrap(Registry.ENTITY_TYPE.getId(entityType).toString());
        }
        else if (s.startsWith("block1")) {
            return IVariable.wrap(I18n.translate(blockSummary));
        }
        else if (s.startsWith("block2")) {
            return IVariable.wrap(I18n.translate(blockDetails));
        }
        return null;
    }
}
