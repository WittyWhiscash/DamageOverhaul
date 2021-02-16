package mod.wittywhiscash.damageoverhaul.common.config;

import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.Level;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DamageOverhaulConfig {

    public DamageOverhaulConfig() {}

    public DebugModule DEBUG = new DebugModule();
    public ArmorEffectivenessModule ARMOR_EFFECTIVENESS = new ArmorEffectivenessModule();
    public DamageEffectivenessModule DAMAGE_EFFECTIVENESS = new DamageEffectivenessModule();
    public DamageModule DAMAGE = new DamageModule();

    public static class DebugModule {

        public DebugModule() {}

        // "Log registration and modifications during startup. Default: false.")
        private boolean loaderDebug = true;

        // "Log damage calculations. Causes lots of log spam. Default: false.")
        private boolean damageDebug = true;

        private boolean colorblindMode = false;

        public boolean getDamageDebug() { return damageDebug; }
        public boolean getLoaderDebug() { return loaderDebug; }
        public boolean getColorblindMode() { return colorblindMode; }

        public void setDamageDebug(boolean damageDebug) { this.damageDebug = damageDebug; }
        public void setLoaderDebug(boolean loaderDebug) { this.loaderDebug = loaderDebug; }
        public void setColorblindMode(boolean colorblindMode) { this.colorblindMode = colorblindMode; }
    }

    public static class ArmorEffectivenessModule {

        public ArmorEffectivenessModule() {}

        // How much the amount of incoming damage affects the effective armor rating. Default: 0.25
        private float damageMagnitude = 0.25F;

        // How much the amount of incoming damage types decreases the effective armor rating. Default: 4.0
        private float damageNumDecreaseMagnitude = 4.0F;

        // How much a single dominant type in the presence of lower damage types will dominate the spread. Default: 2.0
        private float damageTypeMultiplier = 2.0F;

        // How dominant the effect of toughness is. Default: 4.0
        private float toughnessEffectiveness = 4.0F;

        // How much toughness can lower the reduction (at most 1 / e). Default: 2.0
        private float toughnessEffectivenessReduction = 2.0F;

        public float getDamageMagnitude() { return damageMagnitude; }
        public float getDamageNumDecreaseMagnitude() { return damageNumDecreaseMagnitude; }
        public float getDamageTypeMultiplier() { return damageTypeMultiplier; }
        public float getToughnessEffectiveness() { return toughnessEffectiveness; }
        public float getToughnessEffectivenessReduction() { return toughnessEffectivenessReduction; }

        public void setDamageMagnitude(float damageMagnitude) { this.damageMagnitude = damageMagnitude; }
        public void setDamageNumDecreaseMagnitude(float damageNumDecreaseMagnitude) { this.damageNumDecreaseMagnitude = damageNumDecreaseMagnitude; }
        public void setDamageTypeMultiplier(float damageTypeMultiplier) { this.damageTypeMultiplier = damageTypeMultiplier; }
        public void setToughnessEffectiveness(float toughnessEffectiveness) { this.toughnessEffectiveness = toughnessEffectiveness; }
        public void setToughnessEffectivenessReduction(float toughnessEffectivenessReduction) { this.toughnessEffectivenessReduction = toughnessEffectivenessReduction; }
    }

    public static class DamageEffectivenessModule {

        public DamageEffectivenessModule() {}

        // The lower bound of armor reduction, inverted. Eg.: 0.4 = 60% damage reduction. Default: 0.4
        private float finalReduction = 0.4F;

        // The base amount of armor decay. Higher number means a steeper drop off in effectiveness the more armor you have. Default: 0.25
        private float baseDecay = 0.25F;

        // The amount of decay toughness prevents. Default: 0.006
        private float toughnessDecay = 0.006F;

        // In the end, how much toughness increases resistance against damage. Default: 0.004
        private float toughnessReduction = 0.004F;

        public float getBaseDecay() { return baseDecay; }
        public float getFinalReduction() { return finalReduction; }
        public float getToughnessDecay() { return toughnessDecay; }
        public float getToughnessReduction() { return toughnessReduction; }

        public void setBaseDecay(float baseDecay) { this.baseDecay = baseDecay; }
        public void setFinalReduction(float finalReduction) { this.finalReduction = finalReduction; }
        public void setToughnessDecay(float toughnessDecay) { this.toughnessDecay = toughnessDecay; }
        public void setToughnessReduction(float toughnessReduction) { this.toughnessReduction = toughnessReduction; }
    }

    public static class DamageModule {

        private float magicModifier = 0.25F;
        private boolean clampModsOnLoad = true;

        public float getMagicModifier() { return magicModifier; }
        public boolean getClampModsOnLoad() { return clampModsOnLoad; }

        public void setMagicModifier(float magicModifier) { this.magicModifier = magicModifier; }
        public void setClampModsOnLoad(boolean clampModsOnLoad) { this.clampModsOnLoad = clampModsOnLoad; }
    }

    public void saveConfig() {
        Yaml yaml = new Yaml();
        validateConfig(this);
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(FabricLoader.getInstance().getConfigDir().toString(), "damageOverhaul.yaml"));
            yaml.dump(DamageOverhaul.CONFIG, writer);
            writer.close();
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }
    }

    public void loadConfig(DamageOverhaulConfig config) {
        this.ARMOR_EFFECTIVENESS.setDamageMagnitude(config.ARMOR_EFFECTIVENESS.getDamageMagnitude());
        this.ARMOR_EFFECTIVENESS.setDamageNumDecreaseMagnitude(config.ARMOR_EFFECTIVENESS.getDamageNumDecreaseMagnitude());
        this.ARMOR_EFFECTIVENESS.setDamageTypeMultiplier(config.ARMOR_EFFECTIVENESS.getDamageTypeMultiplier());
        this.ARMOR_EFFECTIVENESS.setToughnessEffectiveness(config.ARMOR_EFFECTIVENESS.getToughnessEffectiveness());
        this.ARMOR_EFFECTIVENESS.setToughnessEffectivenessReduction(config.ARMOR_EFFECTIVENESS.getToughnessEffectivenessReduction());

        this.DAMAGE_EFFECTIVENESS.setFinalReduction(config.DAMAGE_EFFECTIVENESS.getFinalReduction());
        this.DAMAGE_EFFECTIVENESS.setBaseDecay(config.DAMAGE_EFFECTIVENESS.getBaseDecay());
        this.DAMAGE_EFFECTIVENESS.setToughnessDecay(config.DAMAGE_EFFECTIVENESS.getToughnessDecay());
        this.DAMAGE_EFFECTIVENESS.setToughnessReduction(config.DAMAGE_EFFECTIVENESS.getToughnessReduction());

        this.DEBUG.setLoaderDebug(config.DEBUG.getLoaderDebug());
        this.DEBUG.setDamageDebug(config.DEBUG.getDamageDebug());
        this.DEBUG.setColorblindMode(config.DEBUG.getColorblindMode());

        this.DAMAGE.setMagicModifier(config.DAMAGE.getMagicModifier());
        this.DAMAGE.setClampModsOnLoad(config.DAMAGE.getClampModsOnLoad());

        validateConfig(this);
    }

    public void validateConfig(DamageOverhaulConfig config) {
         if (config.ARMOR_EFFECTIVENESS.getDamageMagnitude() < 0.0F || config.ARMOR_EFFECTIVENESS.getDamageMagnitude() > 1.0F) {
             DamageOverhaul.log(Level.WARN, "damageMagnitude is not within bounds, correcting!");
             config.ARMOR_EFFECTIVENESS.setDamageMagnitude(MathHelper.clamp(config.ARMOR_EFFECTIVENESS.getDamageMagnitude(), 0.0F, 1.0F));
         }
         if (config.ARMOR_EFFECTIVENESS.getDamageNumDecreaseMagnitude() < 0.0F || config.ARMOR_EFFECTIVENESS.getDamageNumDecreaseMagnitude() > 10.0F) {
             DamageOverhaul.log(Level.WARN, "damageNumDecreaseMagnitude is not within bounds, correcting!");
             config.ARMOR_EFFECTIVENESS.setDamageNumDecreaseMagnitude(MathHelper.clamp(config.ARMOR_EFFECTIVENESS.getDamageNumDecreaseMagnitude(), 0.0F, 10.0F));
         }
         if (config.ARMOR_EFFECTIVENESS.getDamageTypeMultiplier() < 0.0F || config.ARMOR_EFFECTIVENESS.getDamageTypeMultiplier() > 10.0F) {
             DamageOverhaul.log(Level.WARN, "damageTypeMultiplier is not within bounds, correcting!");
             config.ARMOR_EFFECTIVENESS.setDamageTypeMultiplier(MathHelper.clamp(config.ARMOR_EFFECTIVENESS.getDamageTypeMultiplier(), 0.0F, 10.0F));
         }
         if (config.ARMOR_EFFECTIVENESS.getToughnessEffectiveness() < 0.0F || config.ARMOR_EFFECTIVENESS.getToughnessEffectiveness() > 10.0F) {
             DamageOverhaul.log(Level.WARN, "toughnessEffectiveness is not within bounds, correcting!");
             config.ARMOR_EFFECTIVENESS.setToughnessEffectiveness(MathHelper.clamp(config.ARMOR_EFFECTIVENESS.getToughnessEffectiveness(), 0.0F, 10.0F));
         }
         if (config.ARMOR_EFFECTIVENESS.getToughnessEffectivenessReduction() < 0.0F || config.ARMOR_EFFECTIVENESS.getToughnessEffectivenessReduction() > 10.0F) {
             DamageOverhaul.log(Level.WARN, "toughnessEffectivenessReduction is not within bounds, correcting!");
             config.ARMOR_EFFECTIVENESS.setDamageNumDecreaseMagnitude(MathHelper.clamp(config.ARMOR_EFFECTIVENESS.getDamageNumDecreaseMagnitude(), 0.0F, 10.0F));
         }
         if (config.DAMAGE_EFFECTIVENESS.getFinalReduction() < 0.0F || config.DAMAGE_EFFECTIVENESS.getFinalReduction() > 1.0F) {
             DamageOverhaul.log(Level.WARN, "finalReduction is not within bounds, correcting!");
             config.DAMAGE_EFFECTIVENESS.setFinalReduction(MathHelper.clamp(config.DAMAGE_EFFECTIVENESS.getFinalReduction(), 0.0F, 1.0F));
         }
         if (config.DAMAGE_EFFECTIVENESS.getBaseDecay() < 0.0F || config.DAMAGE_EFFECTIVENESS.getBaseDecay() > 1.0F) {
             DamageOverhaul.log(Level.WARN, "baseDecay is not within bounds, correcting!");
             config.DAMAGE_EFFECTIVENESS.setBaseDecay(MathHelper.clamp(config.DAMAGE_EFFECTIVENESS.getBaseDecay(), 0.0F, 1.0F));
         }
         if (config.DAMAGE_EFFECTIVENESS.getToughnessDecay() < 0.0F || config.DAMAGE_EFFECTIVENESS.getToughnessDecay() > 0.1F) {
             DamageOverhaul.log(Level.WARN, "toughnessDecay is not within bounds, correcting!");
             config.DAMAGE_EFFECTIVENESS.setToughnessDecay(MathHelper.clamp(config.DAMAGE_EFFECTIVENESS.getToughnessDecay(), 0.0F, 0.1F));
         }
         if (config.DAMAGE_EFFECTIVENESS.getToughnessReduction() < 0.0F || config.DAMAGE_EFFECTIVENESS.getToughnessReduction() > 0.1F) {
             DamageOverhaul.log(Level.WARN, "toughnessReduction is not within bounds, correcting!");
             config.DAMAGE_EFFECTIVENESS.setToughnessReduction(MathHelper.clamp(config.DAMAGE_EFFECTIVENESS.getToughnessReduction(), 0.0F, 0.1F));
         }
         if (config.DAMAGE.getMagicModifier() < 0.0F || config.DAMAGE.getMagicModifier() > 1.0F) {
             DamageOverhaul.log(Level.WARN, "magicModifier is not within bounds, correcting!");
             config.DAMAGE.setMagicModifier(MathHelper.clamp(config.DAMAGE.getMagicModifier(), 0.0F, 1.0F));
         }
    }
}
