package mod.wittywhiscash.damageoverhaul.common.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TextColor;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class DamageOverhaulConfigScreen {
    public static Screen build(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableText("config.damageoverhaul.title"))
                .setDefaultBackgroundTexture(new Identifier("minecraft", "textures/block/spruce_planks.png"));
        ConfigCategory armor = builder.getOrCreateCategory(new TranslatableText("config.damageoverhaul.title.armor"));
        armor.addEntry(builder.entryBuilder()
                .startFloatField(new TranslatableText("config.damageoverhaul.armor.damageMagnitude"), DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.getDamageMagnitude())
                .setDefaultValue(0.25F)
                .setMin(0.0F)
                .setMax(1.0F)
                .setTooltip(new TranslatableText("config.damageoverhaul.armor.damageMagnitude.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.setDamageMagnitude(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        armor.addEntry(builder.entryBuilder()
                .startFloatField(new TranslatableText("config.damageoverhaul.armor.damageNumDecreaseMagnitude"), DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.getDamageNumDecreaseMagnitude())
                .setDefaultValue(4.0F)
                .setMin(0.0F)
                .setMax(10.0F)
                .setTooltip(new TranslatableText("config.damageoverhaul.armor.damageNumDecreaseMagnitude.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.setDamageNumDecreaseMagnitude(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        armor.addEntry(builder.entryBuilder()
                .startFloatField(new TranslatableText("config.damageoverhaul.armor.damageTypeMultiplier"), DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.getDamageTypeMultiplier())
                .setDefaultValue(2.0F)
                .setMin(0.0F)
                .setMax(10.0F)
                .setTooltip(new TranslatableText("config.damageoverhaul.armor.damageTypeMultiplier.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.setDamageTypeMultiplier(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        armor.addEntry(builder.entryBuilder()
                .startFloatField(new TranslatableText("config.damageoverhaul.armor.toughnessEffectiveness"), DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.getToughnessEffectiveness())
                .setDefaultValue(4.0F)
                .setMin(0.0F)
                .setMax(10.0F)
                .setTooltip(new TranslatableText("config.damageoverhaul.armor.toughnessEffectiveness.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.setToughnessEffectiveness(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        armor.addEntry(builder.entryBuilder()
                .startFloatField(new TranslatableText("config.damageoverhaul.armor.toughnessEffectivenessReduction"), DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.getToughnessEffectivenessReduction())
                .setDefaultValue(2.0F)
                .setMin(0.0F)
                .setMax(10.0F)
                .setTooltip(new TranslatableText("config.damageoverhaul.armor.toughnessEffectivenessReduction.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.ARMOR_EFFECTIVENESS.setToughnessEffectivenessReduction(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        ConfigCategory damage_e = builder.getOrCreateCategory(new TranslatableText("config.damageoverhaul.title.damage_e"));
        damage_e.addEntry(builder.entryBuilder()
                .startFloatField(new TranslatableText("config.damageoverhaul.damage_e.finalReduction"), DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.getFinalReduction())
                .setDefaultValue(0.4F)
                .setMin(0.0F)
                .setMax(1.0F)
                .setTooltip(new TranslatableText("config.damageoverhaul.damage_e.finalReduction.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.setFinalReduction(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        damage_e.addEntry(builder.entryBuilder()
                .startFloatField(new TranslatableText("config.damageoverhaul.damage_e.baseDecay"), DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.getBaseDecay())
                .setDefaultValue(0.25F)
                .setMin(0.0F)
                .setMax(1.0F)
                .setTooltip(new TranslatableText("config.damageoverhaul.damage_e.baseDecay.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.setBaseDecay(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        damage_e.addEntry(builder.entryBuilder()
                .startFloatField(new TranslatableText("config.damageoverhaul.damage_e.toughnessDecay"), DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.getToughnessDecay())
                .setDefaultValue(0.006F)
                .setMin(0.0F)
                .setMax(0.1F)
                .setTooltip(new TranslatableText("config.damageoverhaul.damage_e.toughnessDecay.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.setToughnessDecay(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        damage_e.addEntry(builder.entryBuilder()
                .startFloatField(new TranslatableText("config.damageoverhaul.damage_e.toughnessReduction"), DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.getToughnessReduction())
                .setDefaultValue(0.004F)
                .setMin(0.0F)
                .setMax(0.1F)
                .setTooltip(new TranslatableText("config.damageoverhaul.damage_e.toughnessReduction.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.DAMAGE_EFFECTIVENESS.setToughnessReduction(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        ConfigCategory damage = builder.getOrCreateCategory(new TranslatableText("config.damageoverhaul.title.damage"));
        damage.addEntry(builder.entryBuilder()
                .startFloatField(new TranslatableText("config.damageoverhaul.damage.magicMod"), DamageOverhaul.CONFIG.DAMAGE.getMagicModifier())
                .setDefaultValue(0.25F)
                .setMin(0.0F)
                .setMax(1.0F)
                .setTooltip(new TranslatableText("config.damageoverhaul.damage.magicMod.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.DAMAGE.setMagicModifier(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        ConfigCategory debug = builder.getOrCreateCategory(new TranslatableText("config.damageoverhaul.title.debug"));
        debug.addEntry(builder.entryBuilder()
                .startBooleanToggle(new TranslatableText("config.damageoverhaul.debug.loader"), DamageOverhaul.CONFIG.DEBUG.getLoaderDebug())
                .setDefaultValue(false)
                .setTooltip(new TranslatableText("config.damageoverhaul.debug.loader.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.DEBUG.setLoaderDebug(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        debug.addEntry(builder.entryBuilder()
                .startBooleanToggle(new TranslatableText("config.damageoverhaul.debug.damage"), DamageOverhaul.CONFIG.DEBUG.getDamageDebug())
                .setDefaultValue(false)
                .setTooltip(new TranslatableText("config.damageoverhaul.debug.damage.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.DEBUG.setDamageDebug(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        debug.addEntry(builder.entryBuilder()
                .startBooleanToggle(new TranslatableText("config.damageoverhaul.debug.colorblind"), DamageOverhaul.CONFIG.DEBUG.getColorblindMode())
                .setDefaultValue(false)
                .setTooltip(new TranslatableText("config.damageoverhaul.debug.colorblind.tooltip"))
                .setSaveConsumer(newValue -> {
                    DamageOverhaul.CONFIG.DEBUG.setColorblindMode(newValue);
                    DamageOverhaul.CONFIG.saveConfig();
                })
                .build());
        return builder.build();
    }
}
