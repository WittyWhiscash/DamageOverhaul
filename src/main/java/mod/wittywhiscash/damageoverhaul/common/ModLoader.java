package mod.wittywhiscash.damageoverhaul.common;

import com.google.common.collect.Sets;
import com.google.common.util.concurrent.AtomicDouble;
import com.google.gson.JsonElement;
import com.swordglowsblue.artifice.api.Artifice;
import com.swordglowsblue.artifice.api.ArtificeResourcePack;
import com.swordglowsblue.artifice.api.resource.JsonResource;
import com.swordglowsblue.artifice.impl.ArtificeResourcePackImpl;
import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import mod.wittywhiscash.damageoverhaul.api.DamageCondition;
import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.client.patchouli.PatchouliJSONGenerator;
import mod.wittywhiscash.damageoverhaul.common.config.DamageOverhaulConfig;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.database.defaults.DefaultEntityResistances;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.util.DamageAttribute;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.database.*;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.Level;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.*;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;
import vazkii.patchouli.api.PatchouliAPI;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ModLoader {

    public static void init(FabricLoader loader) {

        // Initialize the database with default values
        AttributeDatabase database = DamageOverhaul.ATTRIBUTE_DATABASE;
        database.init();

        File configDir = loader.getConfigDir().toFile();
        Path configPath = Paths.get(configDir.toString(), DamageOverhaul.MOD_ID);

        // Set up the YAML dumper and reader
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        options.setAllowReadOnlyProperties(true);
        options.setNonPrintableStyle(DumperOptions.NonPrintableStyle.ESCAPE);
        Representer representer = new NonNullRepresenter();
        representer.addClassTag(DamageAttribute.class, Tag.MAP);
        Yaml dumperYaml = new Yaml(representer, options);
        Yaml readerYaml = new Yaml(new DamageAttributeConstructor());

        Writer writer;
        Reader reader;

        // Register particles
        if (loader.isModLoaded("fabric")) {
            Registry.register(Registry.PARTICLE_TYPE, new Identifier(DamageOverhaul.MOD_ID, "vulnerable"), DamageOverhaul.VULNERABLE_PARTICLE);
            Registry.register(Registry.PARTICLE_TYPE, new Identifier(DamageOverhaul.MOD_ID, "weak"), DamageOverhaul.WEAK_PARTICLE);
            Registry.register(Registry.PARTICLE_TYPE, new Identifier(DamageOverhaul.MOD_ID, "resistant"), DamageOverhaul.RESISTANT_PARTICLE);
            Registry.register(Registry.PARTICLE_TYPE, new Identifier(DamageOverhaul.MOD_ID, "immune"), DamageOverhaul.IMMUNE_PARTICLE);
        }

        // Create the general config
        Path generalConfigYaml = Paths.get(configDir.toString(), "damageOverhaul.yaml");
        if (!Files.exists(generalConfigYaml)) {
            try {
                writer = Files.newBufferedWriter(generalConfigYaml);
                String toWrite = dumperYaml.dumpAs(DamageOverhaul.CONFIG, Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        // Read in any changes to the config.
        try {
            reader = Files.newBufferedReader(generalConfigYaml);
            DamageOverhaulConfig config = readerYaml.loadAs(reader, DamageOverhaulConfig.class);
            DamageOverhaul.CONFIG.loadConfig(config);
        } catch (Exception e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        tryCreateDirectory(configPath);

        // Create the damage types file from the default registry, if it doesn't exist yet.
        Path vanillaDamageTypesYaml = Paths.get(configPath.toString(), "damageTypes_vanilla.yaml");
        if (!Files.exists(vanillaDamageTypesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaDamageTypesYaml);
                String toWrite = dumperYaml.dumpAs(database.getDamageTypeDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        // Read in the damage types file before anything else, so there's no errors.
        try {
            reader = Files.newBufferedReader(vanillaDamageTypesYaml);
            parseDamageTypesYaml(readerYaml.loadAs(reader, DamageTypes.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        // Create the damage sources file from the default registry, if it doesn't exist yet.
        Path vanillaDamageSourcesYaml = Paths.get(configPath.toString(), "damageSources_vanilla.yaml");
        if (!Files.exists(vanillaDamageSourcesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaDamageSourcesYaml);
                String toWrite = dumperYaml.dumpAsMap(database.getDamageSourceDatabase());
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        // Create the armor resistances file from the default registry, if it doesn't exist yet.
        Path vanillaArmorResistancesYaml = Paths.get(configPath.toString(), "armorResistances_vanilla.yaml");
        if (!Files.exists(vanillaArmorResistancesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaArmorResistancesYaml);
                String toWrite = dumperYaml.dumpAs(database.getArmorResistanceDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        // Create the entity resistances file from the default registry, if it doesn't exist yet.
        Path vanillaEntityResistancesYaml = Paths.get(configPath.toString(), "entityResistances_vanilla.yaml");
        if (!Files.exists(vanillaEntityResistancesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaEntityResistancesYaml);
                String toWrite = dumperYaml.dumpAs(database.getEntityResistanceDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        // Create the entity damages file from the default registry, if it doesn't exist yet.
        Path vanillaEntityDamagesYaml = Paths.get(configPath.toString(), "entityDamages_vanilla.yaml");
        if (!Files.exists(vanillaEntityDamagesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaEntityDamagesYaml);
                String toWrite = dumperYaml.dumpAs(database.getEntityDamageDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        // Create the tool damages file from the default registry, if it doesn't exist yet.
        Path vanillaToolDamagesYaml = Paths.get(configPath.toString(), "toolDamages_vanilla.yaml");
        if (!Files.exists(vanillaToolDamagesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaToolDamagesYaml);
                String toWrite = dumperYaml.dumpAs(database.getToolDamageDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        // Create the enchantment resistances file from the default registry, if it doesn't exist yet.
        Path vanillaEnchantmentResistancesYaml = Paths.get(configPath.toString(), "enchantmentResistances_vanilla.yaml");
        if (!Files.exists(vanillaEnchantmentResistancesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaEnchantmentResistancesYaml);
                String toWrite = dumperYaml.dumpAs(database.getEnchantmentResistanceDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        // Define and generate the folder structure for custom files.
        Path customYamlPath = Paths.get(configPath.toString(), "custom");

        tryCreateDirectory(customYamlPath);

        Path customArmorResistancesYamlPath = Paths.get(customYamlPath.toString(), "armor");
        Path customToolDamagesYamlPath = Paths.get(customYamlPath.toString(), "tool");

        Path customEntityYamlPath = Paths.get(customYamlPath.toString(), "entity");

        Path customEntityResistancesYamlPath = Paths.get(customEntityYamlPath.toString(), "resistance");
        Path customEntityDamageYamlPath = Paths.get(customEntityYamlPath.toString(), "damage");

        tryCreateDirectory(customArmorResistancesYamlPath);
        tryCreateDirectory(customToolDamagesYamlPath);

        tryCreateDirectory(customEntityYamlPath);

        tryCreateDirectory(customEntityResistancesYamlPath);
        tryCreateDirectory(customEntityDamageYamlPath);

        // Read in the damage source file and make changes to the database based on the file.
        try {
            reader = Files.newBufferedReader(vanillaDamageSourcesYaml);
            parseDamageSourcesYaml(readerYaml.loadAs(reader, DamageSources.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        // Read in the armor resistances file and make changes to the database based on the file.
        try {
            reader = Files.newBufferedReader(vanillaArmorResistancesYaml);
            parseArmorResistancesYaml(readerYaml.loadAs(reader, ArmorResistances.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        // Read in the entity damages file and make changes to the database based on the file.
        try {
            reader = Files.newBufferedReader(vanillaEntityDamagesYaml);
            parseEntityDamagesYaml(readerYaml.loadAs(reader, EntityDamages.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        // Read in the entity resistances file and make changes to the database based on the file.
        try {
            reader = Files.newBufferedReader(vanillaEntityResistancesYaml);
            parseEntityResistancesYaml(readerYaml.loadAs(reader, EntityResistances.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        // Read in the tool damages file and make changes to the database based on the file.
        try {
            reader = Files.newBufferedReader(vanillaToolDamagesYaml);
            parseToolDamagesYaml(readerYaml.loadAs(reader, ToolDamages.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        // Get all the custom files in the armor directory and add to the database the items defined in the files.
        try {
            File[] fileList = customArmorResistancesYamlPath.toFile().listFiles();
            for (File file : fileList) {
                reader = Files.newBufferedReader(file.toPath());
                parseArmorResistancesYaml(readerYaml.loadAs(reader, ArmorResistances.class));
            }
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        // Get all the custom files in the entity resistances directory and add to the database the items defined in the files.
        try {
            File[] fileList = customEntityResistancesYamlPath.toFile().listFiles();
            for (File file : fileList) {
                reader = Files.newBufferedReader(file.toPath());
                parseEntityResistancesYaml(readerYaml.loadAs(reader, EntityResistances.class));
            }
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        // Get all the custom files in the tool directory and add to the database the items defined in the files.
        try {
            File[] fileList = customToolDamagesYamlPath.toFile().listFiles();
            for (File file : fileList) {
                reader = Files.newBufferedReader(file.toPath());
                parseToolDamagesYaml(readerYaml.loadAs(reader, ToolDamages.class));
            }
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        // Get all the custom files in the entity damage directory and add to the database the items defined in the files.
        try {
            File[] fileList = customEntityDamageYamlPath.toFile().listFiles();
            for (File file : fileList) {
                reader = Files.newBufferedReader(file.toPath());
                parseEntityDamagesYaml(readerYaml.loadAs(reader, EntityDamages.class));
            }
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        // Used for data generation. Should only be active within a development environment.
        if (loader.isDevelopmentEnvironment()) {
            ArtificeResourcePack dataPack = new ArtificeResourcePackImpl(ResourceType.SERVER_DATA, new Identifier(DamageOverhaul.MOD_ID, "guidebook"), resourcePackBuilder -> {
                resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/book.json"), new JsonResource(PatchouliJSONGenerator.generateRootBookJson()));
                resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/categories/entities.json"), new JsonResource(PatchouliJSONGenerator.generateCategory("Entities", Items.ZOMBIE_SPAWN_EGG)));
                resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/categories/tool_damages.json"), new JsonResource(PatchouliJSONGenerator.generateCategory("Tool Damages", Items.IRON_SWORD)));
                resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/categories/armor_resistances.json"), new JsonResource(PatchouliJSONGenerator.generateCategory("Armor Resistances", Items.IRON_CHESTPLATE)));
                resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/categories/enchantment_resistances.json"), new JsonResource(PatchouliJSONGenerator.generateCategory("Enchantment Resistances", Items.ENCHANTED_BOOK)));
                for (EntityType<?> entityType : EntityResistances.values()) {
                    JsonElement entry = PatchouliJSONGenerator.generateEntityEntry(entityType);
                    PatchouliJSONGenerator.addEntityLandingPage(entry, entityType);
                    if (!DefaultEntityResistances.valueOf(Registry.ENTITY_TYPE.getId(entityType).getPath().toUpperCase(Locale.ROOT)).getDamageAssociations().isEmpty()) {
                        PatchouliJSONGenerator.addEntityResistancePage(entry, entityType);
                        for (EntityType<?> type : DefaultEntityResistances.valueOf(Registry.ENTITY_TYPE.getId(entityType).getPath().toUpperCase(Locale.ROOT)).getDamageAssociations()) {
                            PatchouliJSONGenerator.addEntityDamagePage(entry, type);
                        }
                    } else {
                        PatchouliJSONGenerator.addEntityResistancePage(entry, entityType);
                    }
                    resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/entries/entities/" + Registry.ENTITY_TYPE.getId(entityType).getPath() + ".json"), new JsonResource(entry));

                }
                for (Item toolItem : ToolDamages.values()) {
                    resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/entries/tool_damages/" + Registry.ITEM.getId(toolItem).getPath() + ".json"), new JsonResource(PatchouliJSONGenerator.generateToolEntryJson(toolItem)));
                }
                for (Item armorItem : ArmorResistances.values()) {
                    resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/entries/armor_resistances/" + Registry.ITEM.getId(armorItem).getPath() + ".json"), new JsonResource(PatchouliJSONGenerator.generateArmorEntryJson(armorItem, ((ArmorItem) armorItem).getMaterial())));
                }
                for (Enchantment enchantment : EnchantmentResistances.values()) {
                    resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/entries/enchantment_resistances/" + Registry.ENCHANTMENT.getId(enchantment).getPath() + ".json"), new JsonResource(PatchouliJSONGenerator.generateEnchantmentEntryJson(enchantment)));
                }
                try {
                    resourcePackBuilder.dumpResources("./resources", "data");
                } catch (IOException e) {
                    DamageOverhaul.log(Level.ERROR, e.getMessage());
                }
            });
            Artifice.registerData(new Identifier(DamageOverhaul.MOD_ID, "guidebook"), dataPack);
        }

        if (loader.isModLoaded("patchouli")) {
            Set<EntityType<?>> entitiesToFilter = new HashSet<>();
            for (EntityType<?> entityType : EntityResistances.values()) {
                PatchouliAPI.get().setConfigFlag(new Identifier(DamageOverhaul.MOD_ID, Registry.ENTITY_TYPE.getId(entityType).getPath()).toString(), true);
                entitiesToFilter.add(entityType);
            }

            for (EntityType<?> entityType : EntityDamages.values()) {
                if (!entitiesToFilter.contains(entityType)) {
                    PatchouliAPI.get().setConfigFlag(new Identifier(DamageOverhaul.MOD_ID, Registry.ENTITY_TYPE.getId(entityType).getPath()).toString(), true);
                }
            }

            for (Item toolItem : ToolDamages.values()) {
                PatchouliAPI.get().setConfigFlag(new Identifier(DamageOverhaul.MOD_ID, Registry.ITEM.getId(toolItem).getPath()).toString(), true);
            }

            for (Item armorItem : ArmorResistances.values()) {
                PatchouliAPI.get().setConfigFlag(new Identifier(DamageOverhaul.MOD_ID, Registry.ITEM.getId(armorItem).getPath()).toString(), true);
            }

            for (Enchantment enchantment : EnchantmentResistances.values()) {
                PatchouliAPI.get().setConfigFlag(new Identifier(DamageOverhaul.MOD_ID, Registry.ENCHANTMENT.getId(enchantment).getPath()).toString(), true);
            }
            Registry.register(Registry.ITEM, new Identifier(DamageOverhaul.MOD_ID, "guidebook"), DamageOverhaul.GUIDEBOOK_ITEM);
        }
    }

    private static void tryCreateDirectory(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }
    }

    private static void parseArmorResistancesYaml(ArmorResistances resistances) {
        for (String id : resistances.getArmorResistanceDatabase().keySet()) {
            Identifier identifier = new Identifier(id);
            if (Registry.ITEM.containsId(identifier)) {
                Item armorItem = Registry.ITEM.get(identifier);
                Set<DamageType> damageTypes = Sets.newHashSet(DamageTypes.values());
                Set<DamageType> presentDamageTypes = new HashSet<>();
                if (!DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().contains(armorItem)) {
                    Map<DamageType, DamageAttribute> newArmorMap = new HashMap<>();
                    for (Map.Entry<DamageType, DamageAttribute> entry : resistances.getResistanceSpread(armorItem).entrySet()) {
                        if (DamageOverhaul.CONFIG.DAMAGE.getClampModsOnLoad() && entry.getValue().getModifier() > ((ArmorItem)armorItem).getProtection()) {
                            float armorValue = Math.min(entry.getValue().getModifier(), ((ArmorItem)armorItem).getProtection());
                            DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("[Armor Resistance Loader] Found armor piece %s with modifier for type %2s larger than normal, clamping to upper bound!", armorItem.getTranslationKey(), entry.getKey().getRegistryName()));
                            newArmorMap.put(entry.getKey(), new DamageAttribute(armorValue));
                        }
                        else {
                            newArmorMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                    DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().registerArmorResistance(armorItem, newArmorMap);
                }
                else {
                    for (String damageType : resistances.getArmorResistanceDatabase().get(id).keySet()) {
                        float resistanceDef = resistances.getArmorResistanceDatabase().get(id).get(damageType).getModifier();
                        if (DamageOverhaul.CONFIG.DAMAGE.getClampModsOnLoad() && resistanceDef > ((ArmorItem)armorItem).getProtection()) {
                            resistanceDef = Math.min(resistanceDef, ((ArmorItem)armorItem).getProtection());
                            DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("[Armor Resistance Loader] Found armor piece %s with modifier for type %2s larger than normal, clamping to upper bound!", armorItem.getTranslationKey(), damageType));
                        }
                        boolean isKeyPresent = DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().getResistanceSpread(armorItem).containsKey(DamageTypes.valueOf(damageType));
                        if (isKeyPresent && resistanceDef != DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().getResistanceSpread(armorItem).get(DamageTypes.valueOf(damageType)).getModifier()) {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().getResistanceSpread(armorItem).replace(DamageTypes.valueOf(damageType), new DamageAttribute(resistanceDef));
                        }
                        else {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().getResistanceSpread(armorItem).put(DamageTypes.valueOf(damageType), new DamageAttribute(resistanceDef));
                        }
                        presentDamageTypes.add(DamageTypes.valueOf(damageType));
                    }
                    damageTypes.removeAll(presentDamageTypes);
                    if (!damageTypes.isEmpty()) {
                        for (DamageType type : damageTypes) {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().getResistanceSpread(armorItem).remove(type);
                        }
                    }
                }
            }
            else {
                DamageOverhaul.log(Level.WARN, String.format("[Armor Resistance Loader] Registry does not contain armor item with id: %s, skipping!", identifier.toString()));
            }
        }
    }

    private static void parseEntityDamagesYaml(EntityDamages damages) {
        for (String id : damages.getEntityDamageDatabase().keySet()) {
            Identifier identifier = new Identifier(id);
            if (Registry.ENTITY_TYPE.containsId(identifier)) {
                EntityType<?> entityType = Registry.ENTITY_TYPE.get(identifier);
                Set<DamageType> damageTypes = Sets.newHashSet(DamageTypes.values());
                Set<DamageType> presentDamageTypes = new HashSet<>();
                if (!DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().contains(entityType)) {
                    Map<DamageType, DamageAttribute> newDamageMap = new HashMap<>();
                    AtomicDouble damageTotal = new AtomicDouble(0);
                    for (Map.Entry<DamageType, DamageAttribute> entry : damages.getDamageSpread(entityType).entrySet()) {
                        newDamageMap.put(entry.getKey(), entry.getValue());
                        damageTotal.addAndGet(entry.getValue().getModifier());
                    }
                    if (DamageOverhaul.CONFIG.DAMAGE.getClampModsOnLoad() && damageTotal.floatValue() > 1F) {
                        float difference = damageTotal.floatValue() - 1;
                        Map.Entry<DamageType, DamageAttribute> largestEntry = Collections.max(newDamageMap.entrySet(), Comparator.comparing((Map.Entry<DamageType, DamageAttribute> e) -> e.getValue().getModifier()));
                        float newValue = largestEntry.getValue().getModifier() - difference;
                        newDamageMap.replace(largestEntry.getKey(), new DamageAttribute(newValue));
                        DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("[Entity Damage Loader] Found entity %s with damage total larger than 100%%, clamping largest value %2s to upper bound!", entityType.getTranslationKey(), largestEntry.getKey().getRegistryName()));
                    }
                    DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().registerEntityDamageSpread(entityType, newDamageMap);
                }
                else {
                    AtomicDouble damageTotal = new AtomicDouble(0);
                    for (String damageType : damages.getEntityDamageDatabase().get(id).keySet()) {
                        float damageDef = damages.getEntityDamageDatabase().get(id).get(damageType).getModifier();
                        boolean isKeyPresent = DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).containsKey(DamageTypes.valueOf(damageType));
                        if (isKeyPresent && damageDef != DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).get(DamageTypes.valueOf(damageType)).getModifier()) {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).replace(DamageTypes.valueOf(damageType), new DamageAttribute(damageDef));
                        }
                        else {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).put(DamageTypes.valueOf(damageType), new DamageAttribute(damageDef));
                        }
                        damageTotal.addAndGet(damageDef);
                        presentDamageTypes.add(DamageTypes.valueOf(damageType));
                    }
                    damageTypes.removeAll(presentDamageTypes);
                    if (!damageTypes.isEmpty()) {
                        for (DamageType type : damageTypes) {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).remove(type);
                        }
                    }
                    if (DamageOverhaul.CONFIG.DAMAGE.getClampModsOnLoad() && damageTotal.floatValue() > 1F) {
                        float difference = damageTotal.floatValue() - 1;
                        Map.Entry<DamageType, DamageAttribute> largestEntry = Collections.max(DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).entrySet(), Comparator.comparing((Map.Entry<DamageType, DamageAttribute> e) -> e.getValue().getModifier()));
                        float newValue = largestEntry.getValue().getModifier() - difference;
                        DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).replace(largestEntry.getKey(), new DamageAttribute(newValue));
                        DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("[Entity Damage Loader] Found entity %s with damage total larger than 100%%, clamping largest value %2s to upper bound!", entityType.getTranslationKey(), largestEntry.getKey().getRegistryName()));
                    }
                }
            }
            else {
                DamageOverhaul.log(Level.WARN, String.format("[Entity Damage Loader] Registry does not contain entity with id: %s, skipping!", identifier.toString()));
            }
        }
    }

    private static void parseEntityResistancesYaml(EntityResistances resistances) {
        for (String id : resistances.getEntityResistanceDatabase().keySet()) {
            Identifier identifier = new Identifier(id);
            if (Registry.ENTITY_TYPE.containsId(identifier)) {
                EntityType<?> entityType = Registry.ENTITY_TYPE.get(identifier);
                Set<DamageType> damageTypes = Sets.newHashSet(DamageTypes.values());
                Set<DamageType> presentDamageTypes = new HashSet<>();
                if (!DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().contains(entityType)) {
                    Map<DamageType, DamageAttribute> newResistanceMap = new HashMap<>();
                    for (Map.Entry<String, DamageAttribute> entry : resistances.getEntityResistanceDatabase().get(identifier.toString()).entrySet()) {
                        if (DamageOverhaul.CONFIG.DAMAGE.getClampModsOnLoad()) {
                            switch (entry.getValue().getDamageCondition()) {
                                case WEAK:
                                case RESISTANT:
                                    if (entry.getValue().getModifier() > 1F) {
                                        float newValue = Math.min(entry.getValue().getModifier(), 1.0F);
                                        newResistanceMap.put(DamageTypes.valueOf(entry.getKey()), new DamageAttribute(entry.getValue().getDamageCondition(), newValue));
                                        DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("[Entity Resistance Loader] Found entity %s with type resistance of %2s with a condition of %3s and a value greater than 1, clamping to 1!", entityType.getTranslationKey(), entry.getKey(), entry.getValue().getDamageCondition().toString().toLowerCase()));
                                    }
                                    break;
                                default:
                                    newResistanceMap.put(DamageTypes.valueOf(entry.getKey()), new DamageAttribute(entry.getValue().getDamageCondition(), null));
                                    DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("[Entity Resistance Loader] Found entity %s with type resistance of %2s with a condition of %3s and a value greater than 1, clamping to 1!", entityType.getTranslationKey(), entry.getKey(), entry.getValue().getDamageCondition().toString().toLowerCase()));
                                    break;
                            }
                        }
                        else {
                            newResistanceMap.put(DamageTypes.valueOf(entry.getKey()), entry.getValue());
                        }
                    }
                    DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().registerEntityResistanceSpread(entityType, newResistanceMap);
                }
                else {
                    for (String damageType : resistances.getEntityResistanceDatabase().get(id).keySet()) {
                        DamageCondition conditionDef = resistances.getEntityResistanceDatabase().get(id).get(damageType).getDamageCondition();
                        Float resistanceAmount = resistances.getEntityResistanceDatabase().get(id).get(damageType).getModifier();
                        if (DamageOverhaul.CONFIG.DAMAGE.getClampModsOnLoad() && resistanceAmount != null) {
                            if (resistanceAmount > 1.0F) {
                                resistanceAmount = Math.min(resistanceAmount, 1.0F);
                                DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("[Entity Resistance Loader] Found entity %s with type resistance of %2s with a condition of %3s and a value greater than 1, clamping to 1!", entityType.getTranslationKey(), damageType, conditionDef.toString().toLowerCase()));
                            }
                        }
                        boolean isKeyPresent = DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().getResistanceSpread(entityType).containsKey(DamageTypes.valueOf(damageType));
                        if (isKeyPresent && conditionDef != DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().getResistanceSpread(entityType).get(DamageTypes.valueOf(damageType)).getDamageCondition()) {
                            if (Objects.isNull(resistanceAmount) || !resistanceAmount.equals(DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().getResistanceSpread(entityType).get(DamageTypes.valueOf(damageType)).getModifier())) {
                                DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().getResistanceSpread(entityType).replace(DamageTypes.valueOf(damageType), new DamageAttribute(conditionDef, resistanceAmount));
                            }
                            else {
                                DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().getResistanceSpread(entityType).replace(DamageTypes.valueOf(damageType), new DamageAttribute(conditionDef, -1F));
                            }
                        }
                        else {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().getResistanceSpread(entityType).put(DamageTypes.valueOf(damageType), new DamageAttribute(conditionDef, resistanceAmount));
                        }
                        presentDamageTypes.add(DamageTypes.valueOf(damageType));
                    }
                    damageTypes.removeAll(presentDamageTypes);
                    if (!damageTypes.isEmpty()) {
                        for (DamageType type : damageTypes) {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().getResistanceSpread(entityType).remove(type);
                        }
                    }
                }
            }
            else {
                DamageOverhaul.log(Level.WARN, String.format("[Entity Resistance Loader] Registry does not contain entity with id: %s, skipping!", identifier.toString()));
            }
        }
    }

    private static void parseToolDamagesYaml(ToolDamages damages) {
        for (String id : damages.getToolDamageDatabase().keySet()) {
            Identifier identifier = new Identifier(id);
            if (Registry.ITEM.containsId(identifier)) {
                Item toolItem = Registry.ITEM.get(identifier);
                Set<DamageType> damageTypes = Sets.newHashSet(DamageTypes.values());
                Set<DamageType> presentDamageTypes = new HashSet<>();
                if (!DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().contains(toolItem)) {
                    Map<DamageType, DamageAttribute> newDamageMap = new HashMap<>();
                    AtomicDouble damageTotal = new AtomicDouble(0);
                    for (Map.Entry<DamageType, DamageAttribute> entry : damages.getDamageSpread(toolItem).entrySet()) {
                        newDamageMap.put(entry.getKey(), entry.getValue());
                        damageTotal.addAndGet(entry.getValue().getModifier());
                    }
                    if (DamageOverhaul.CONFIG.DAMAGE.getClampModsOnLoad() && damageTotal.floatValue() > 1F) {
                        float difference = damageTotal.floatValue() - 1;
                        Map.Entry<DamageType, DamageAttribute> largestEntry = Collections.max(newDamageMap.entrySet(), Comparator.comparing((Map.Entry<DamageType, DamageAttribute> e) -> e.getValue().getModifier()));
                        float newValue = largestEntry.getValue().getModifier() - difference;
                        newDamageMap.replace(largestEntry.getKey(), new DamageAttribute(newValue));
                        DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("[Tool Damage Loader] Found tool %s with damage total larger than 100%%, clamping largest value %2s to upper bound!", toolItem.getTranslationKey(), largestEntry.getKey().getRegistryName()));
                    }
                    DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().registerToolDamageSpread(toolItem, newDamageMap);
                }
                else {
                    AtomicDouble damageTotal = new AtomicDouble(0);
                    for (String damageType : damages.getToolDamageDatabase().get(id).keySet()) {
                        float damageDef = damages.getToolDamageDatabase().get(id).get(damageType).getModifier();
                        boolean isKeyPresent = DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).containsKey(DamageTypes.valueOf(damageType));
                        if (isKeyPresent && damageDef != DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).get(DamageTypes.valueOf(damageType)).getModifier()) {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).replace(DamageTypes.valueOf(damageType), new DamageAttribute(damageDef));
                        }
                        else {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).put(DamageTypes.valueOf(damageType), new DamageAttribute(damageDef));
                        }
                        damageTotal.addAndGet(damageDef);
                        presentDamageTypes.add(DamageTypes.valueOf(damageType));
                    }
                    damageTypes.removeAll(presentDamageTypes);
                    if (!damageTypes.isEmpty()) {
                        for (DamageType type : damageTypes) {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).remove(type);
                        }
                    }
                    if (DamageOverhaul.CONFIG.DAMAGE.getClampModsOnLoad() && damageTotal.floatValue() > 1.0F) {
                        float difference = damageTotal.floatValue() - 1;
                        Map.Entry<DamageType, DamageAttribute> largestEntry = Collections.max(DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).entrySet(), Comparator.comparing((Map.Entry<DamageType, DamageAttribute> e) -> e.getValue().getModifier()));
                        float newValue = largestEntry.getValue().getModifier() - difference;
                        DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).replace(largestEntry.getKey(), new DamageAttribute(newValue));
                        DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("[Tool Damage Loader] Found tool %s with damage total larger than 100%%, clamping largest value %2s to upper bound!", toolItem.getTranslationKey(), largestEntry.getKey().getRegistryName()));
                    }
                }
            }
            else {
                DamageOverhaul.log(Level.WARN, String.format("[Tool Damage Loader] Registry does not contain armor item with id: %s, skipping!", identifier.toString()));
            }
        }
    }

    private static void parseDamageSourcesYaml(DamageSources sources) {
        for (DamageSource source : DamageSources.values()) {
            if (sources.contains(source)) {
                Set<DamageType> damageTypes = Sets.newHashSet(DamageTypes.values());
                Set<DamageType> presentDamageTypes = new HashSet<>();
                AtomicDouble damageTotal = new AtomicDouble(0);
                for (String damageType : sources.getDamageSourceDatabase().get(source.getName()).keySet()) {
                    float damageDef = sources.getDamageSourceDatabase().get(source.getName()).get(damageType).getModifier();
                    boolean isKeyPresent = DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).containsKey(DamageTypes.valueOf(damageType));
                    if (isKeyPresent && damageDef != DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).get(DamageTypes.valueOf(damageType)).getModifier()) {
                        DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).replace(DamageTypes.valueOf(damageType), new DamageAttribute(damageDef));
                    }
                    else {
                        DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).put(DamageTypes.valueOf(damageType), new DamageAttribute(damageDef));
                    }
                    damageTotal.addAndGet(damageDef);
                    presentDamageTypes.add(DamageTypes.valueOf(damageType));
                }
                damageTypes.removeAll(presentDamageTypes);
                if (!damageTypes.isEmpty()) {
                    for (DamageType type : damageTypes) {
                        DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).remove(type);
                    }
                }
                if (DamageOverhaul.CONFIG.DAMAGE.getClampModsOnLoad() && damageTotal.floatValue() > 1.0F) {
                    float difference = damageTotal.floatValue() - 1;
                    Map.Entry<DamageType, DamageAttribute> largestEntry = Collections.max(DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).entrySet(), Comparator.comparing((Map.Entry<DamageType, DamageAttribute> e) -> e.getValue().getModifier()));
                    float newValue = largestEntry.getValue().getModifier() - difference;
                    DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).replace(largestEntry.getKey(), new DamageAttribute(newValue));
                    DamageOverhaul.debugLog(Level.INFO, DamageOverhaul.CONFIG.DEBUG.getLoaderDebug(), String.format("[Damage Source Loader] Found source %s with damage total larger than 100%%, clamping largest value %2s to upper bound!", source.getName(), largestEntry.getKey().getRegistryName()));
                }
            }
        }
    }

    private static void parseDamageTypesYaml(DamageTypes types) {
        for (String registryName : types.getDamageTypeRegistry().values()) {
            if (!DamageOverhaul.ATTRIBUTE_DATABASE.getDamageTypeDatabase().contains(registryName)) {
                DamageOverhaul.ATTRIBUTE_DATABASE.getDamageTypeDatabase().registerDamageType(registryName);
            }
        }
    }

    static class NonNullRepresenter extends Representer {
        public NonNullRepresenter() {
            super();
            this.nullRepresenter = new NonNullRepresent();
        }

        class NonNullRepresent implements Represent {

            @Override
            public Node representData(Object data) {
                return representScalar(Tag.NULL, "~");
            }
        }
    }

    static class DamageAttributeConstructor extends Constructor {
        public DamageAttributeConstructor() {
            yamlClassConstructors.put(NodeId.mapping, new AttributeConstruct());
        }

        class AttributeConstruct extends Constructor.ConstructMapping {
            @Override
            protected Object constructJavaBean2ndStep(MappingNode node, Object object) {
                Class<?> clazz = node.getType();
                if (clazz.equals(DamageSources.class)) {
                    Map<Object, Object> map = constructMapping(node);
                    Map<String, Map<String, DamageAttribute>> databaseToReturn = new LinkedHashMap<>();
                    Map<Object, Object> database = (Map<Object, Object>) map.get("damageSourceDatabase");
                    for (DamageSource source : DamageSources.values()) {
                        Map<Object, Object> sourceData = (Map<Object, Object>) database.get(source.name);
                        Map<String, DamageAttribute> attributeMap = new LinkedHashMap<>();
                        for (DamageType type : DamageTypes.values()) {
                            if (sourceData.containsKey(type.getRegistryName())) {
                                Map<Object, Object> attributeData = (Map<Object, Object>) sourceData.get(type.getRegistryName());
                                Float modifier;
                                for (Object key : attributeData.keySet()) {
                                    if (key.equals("modifier")) {
                                        modifier = ((Double) attributeData.get(key)).floatValue();
                                        attributeMap.put(type.getRegistryName(), new DamageAttribute(null, modifier));
                                    }
                                }
                            }
                        }
                        databaseToReturn.put(source.name, attributeMap);
                    }
                    return new DamageSources(databaseToReturn);
                }
                if (clazz.equals(ArmorResistances.class)) {
                    Map<Object, Object> map = constructMapping(node);
                    Map<String, Map<String, DamageAttribute>> databaseToReturn = new LinkedHashMap<>();
                    Map<Object, Object> database = (Map<Object, Object>) map.get("armorResistanceDatabase");
                    for (Item item : ArmorResistances.values()) {
                        String itemID = Registry.ITEM.getId(item).toString();
                        Map<Object, Object> sourceData = (Map<Object, Object>) database.get(itemID);
                        Map<String, DamageAttribute> attributeMap = new LinkedHashMap<>();
                        for (DamageType type : DamageTypes.values()) {
                            if (sourceData.containsKey(type.getRegistryName())) {
                                Map<Object, Object> attributeData = (Map<Object, Object>) sourceData.get(type.getRegistryName());
                                Float modifier;
                                for (Object key : attributeData.keySet()) {
                                    if (key.equals("modifier")) {
                                        modifier = ((Double) attributeData.get(key)).floatValue();
                                        attributeMap.put(type.getRegistryName(), new DamageAttribute(null, modifier));
                                    }
                                }
                            }
                        }
                        databaseToReturn.put(itemID, attributeMap);
                    }
                    return new ArmorResistances(databaseToReturn);
                }
                if (clazz.equals(EntityDamages.class)) {
                    Map<Object, Object> map = constructMapping(node);
                    Map<String, Map<String, DamageAttribute>> databaseToReturn = new LinkedHashMap<>();
                    Map<Object, Object> database = (Map<Object, Object>) map.get("entityDamageDatabase");
                    for (EntityType<?> entity : EntityDamages.values()) {
                        String entityID = Registry.ENTITY_TYPE.getId(entity).toString();
                        Map<Object, Object> sourceData = (Map<Object, Object>) database.get(entityID);
                        Map<String, DamageAttribute> attributeMap = new LinkedHashMap<>();
                        for (DamageType type : DamageTypes.values()) {
                            if (sourceData.containsKey(type.getRegistryName())) {
                                Map<Object, Object> attributeData = (Map<Object, Object>) sourceData.get(type.getRegistryName());
                                Float modifier;
                                for (Object key : attributeData.keySet()) {
                                    if (key.equals("modifier")) {
                                        modifier = ((Double) attributeData.get(key)).floatValue();
                                        attributeMap.put(type.getRegistryName(), new DamageAttribute(null, modifier));
                                    }
                                }
                            }
                        }
                        databaseToReturn.put(entityID, attributeMap);
                    }
                    return new EntityDamages(databaseToReturn);
                }
                if (clazz.equals(EntityResistances.class)) {
                    Map<Object, Object> map = constructMapping(node);
                    Map<String, Map<String, DamageAttribute>> databaseToReturn = new LinkedHashMap<>();
                    Map<Object, Object> database = (Map<Object, Object>) map.get("entityResistanceDatabase");
                    for (EntityType<?> entity : EntityResistances.values()) {
                        String entityID = Registry.ENTITY_TYPE.getId(entity).toString();
                        Map<Object, Object> sourceData = (Map<Object, Object>) database.get(entityID);
                        Map<String, DamageAttribute> attributeMap = new LinkedHashMap<>();
                        for (DamageType type : DamageTypes.values()) {
                            if (sourceData.containsKey(type.getRegistryName())) {
                                Map<Object, Object> attributeData = (Map<Object, Object>) sourceData.get(type.getRegistryName());
                                DamageCondition condition = null;
                                Float modifier = null;
                                for (Object key : attributeData.keySet()) {
                                    if (key.equals("damageCondition")) {
                                        condition = DamageCondition.valueOf((String) attributeData.get(key));
                                    } else {
                                        modifier = Objects.nonNull(attributeData.get(key)) ? ((Double) attributeData.get(key)).floatValue() : null;
                                    }
                                }
                                attributeMap.put(type.getRegistryName(), new DamageAttribute(condition, modifier));
                            } else {
                                attributeMap.put(type.getRegistryName(), new DamageAttribute(DamageCondition.NEUTRAL, null));
                            }
                        }
                        databaseToReturn.put(entityID, attributeMap);
                    }
                    return new EntityResistances(databaseToReturn);
                }
                if (clazz.equals(ToolDamages.class)) {
                    Map<Object, Object> map = constructMapping(node);
                    Map<String, Map<String, DamageAttribute>> databaseToReturn = new LinkedHashMap<>();
                    Map<Object, Object> database = (Map<Object, Object>) map.get("toolDamageDatabase");
                    for (Item item : ToolDamages.values()) {
                        String itemID = Registry.ITEM.getId(item).toString();
                        Map<Object, Object> sourceData = (Map<Object, Object>) database.get(itemID);
                        Map<String, DamageAttribute> attributeMap = new LinkedHashMap<>();
                        for (DamageType type : DamageTypes.values()) {
                            if (sourceData.containsKey(type.getRegistryName())) {
                                Map<Object, Object> attributeData = (Map<Object, Object>) sourceData.get(type.getRegistryName());
                                Float modifier;
                                for (Object key : attributeData.keySet()) {
                                    if (key.equals("modifier")) {
                                        modifier = ((Double) attributeData.get(key)).floatValue();
                                        attributeMap.put(type.getRegistryName(), new DamageAttribute(null, modifier));
                                    }
                                }
                            }
                        }
                        databaseToReturn.put(itemID, attributeMap);
                    }
                    return new ToolDamages(databaseToReturn);
                }
                return super.constructJavaBean2ndStep(node, object);
            }
        }
    }
}
