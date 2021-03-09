package mod.wittywhiscash.damageoverhaul;

import mod.wittywhiscash.damageoverhaul.client.particle.DamageIndicatorParticle;
import mod.wittywhiscash.damageoverhaul.common.ModLoader;
import mod.wittywhiscash.damageoverhaul.common.config.DamageOverhaulConfig;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.database.*;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.item.GuidebookItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.particle.DefaultParticleType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;

public class DamageOverhaul implements ModInitializer, ClientModInitializer {

    public static Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "damageoverhaul";
    public static final String MOD_NAME = "Damage Overhaul";
    public static final AttributeDatabase ATTRIBUTE_DATABASE = AttributeDatabase.getInstance();
    public static final DamageOverhaulConfig CONFIG = new DamageOverhaulConfig();
    public static final DecimalFormat DF = new DecimalFormat("###");

    public static final Item GUIDEBOOK_ITEM = new GuidebookItem();

    public static final DefaultParticleType VULNERABLE_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType WEAK_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType RESISTANT_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType IMMUNE_PARTICLE = FabricParticleTypes.simple();


    public static void log(Level level, String message) {
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

    public static void debugLog(Level level, boolean option, String message) {
        if (option) {
            LOGGER.log(level, "["+MOD_NAME+"] " + message);
        }
    }

    @Override
    public void onInitialize() {
        FabricLoader loader = FabricLoader.getInstance();
        ModLoader.init(loader);
        log(Level.INFO, "Initialized common items");
    }

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(VULNERABLE_PARTICLE, DamageIndicatorParticle.DefaultFactory::new);
        ParticleFactoryRegistry.getInstance().register(WEAK_PARTICLE, DamageIndicatorParticle.DefaultFactory::new);
        ParticleFactoryRegistry.getInstance().register(RESISTANT_PARTICLE, DamageIndicatorParticle.DefaultFactory::new);
        ParticleFactoryRegistry.getInstance().register(IMMUNE_PARTICLE, DamageIndicatorParticle.DefaultFactory::new);
        log(Level.INFO, "Initialized client items");
    }
}