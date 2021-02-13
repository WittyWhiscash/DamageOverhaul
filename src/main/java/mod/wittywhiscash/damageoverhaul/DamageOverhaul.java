package mod.wittywhiscash.damageoverhaul;

import mod.wittywhiscash.damageoverhaul.client.ClientLoader;
import mod.wittywhiscash.damageoverhaul.common.CommonLoader;
import mod.wittywhiscash.damageoverhaul.common.config.DamageOverhaulConfig;
import mod.wittywhiscash.damageoverhaul.common.database.*;
import mod.wittywhiscash.damageoverhaul.common.item.GuidebookItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
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
        CommonLoader.init(loader);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(DamageOverhaul.MOD_ID, "vulnerable"), VULNERABLE_PARTICLE);
        if (loader.isModLoaded("patchouli")) {
            Registry.register(Registry.ITEM, new Identifier(DamageOverhaul.MOD_ID, "guidebook"), GUIDEBOOK_ITEM);
        }
        log(Level.INFO, "Initialized common items");
    }

    @Override
    public void onInitializeClient() {
        ClientLoader.init();
        log(Level.INFO, "Initialized client items");
    }
}