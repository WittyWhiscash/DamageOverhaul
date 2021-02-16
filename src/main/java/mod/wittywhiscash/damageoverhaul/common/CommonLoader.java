package mod.wittywhiscash.damageoverhaul.common;

import com.google.common.collect.Sets;
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
import mod.wittywhiscash.damageoverhaul.common.damage.DamageAttribute;
import mod.wittywhiscash.damageoverhaul.common.database.*;
import mod.wittywhiscash.damageoverhaul.common.database.defaults.DefaultEntityResistances;
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
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.nodes.Tag;
import vazkii.patchouli.api.PatchouliAPI;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CommonLoader {

    public static void init(FabricLoader loader) {

        AttributeDatabase database = DamageOverhaul.ATTRIBUTE_DATABASE;
        database.init();
        File configDir = loader.getConfigDir().toFile();
        Path configPath = Paths.get(configDir.toString(), DamageOverhaul.MOD_ID);
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        options.setAllowReadOnlyProperties(true);
        Yaml yaml = new Yaml(options);
        Writer writer;
        Reader reader;

        Path generalConfigYaml = Paths.get(configDir.toString(), "damageOverhaul.yaml");
        if (!Files.exists(generalConfigYaml)) {
            try {
                writer = Files.newBufferedWriter(generalConfigYaml);
                String toWrite = yaml.dumpAs(DamageOverhaul.CONFIG, Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        try {
            reader = Files.newBufferedReader(generalConfigYaml);
            DamageOverhaulConfig config = yaml.loadAs(reader, DamageOverhaulConfig.class);
            DamageOverhaul.CONFIG.loadConfig(config);
        } catch (Exception e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        tryCreateDirectory(configPath);

        Path vanillaDamageTypesYaml = Paths.get(configPath.toString(), "damageTypes_vanilla.yaml");
        if (!Files.exists(vanillaDamageTypesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaDamageTypesYaml);
                String toWrite = yaml.dumpAs(database.getDamageTypeDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        try {
            reader = Files.newBufferedReader(vanillaDamageTypesYaml);
            parseDamageTypesYaml(yaml.loadAs(reader, DamageTypes.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        Path vanillaDamageSourcesYaml = Paths.get(configPath.toString(), "damageSources_vanilla.yaml");
        if (!Files.exists(vanillaDamageSourcesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaDamageSourcesYaml);
                String toWrite = yaml.dumpAsMap(database.getDamageSourceDatabase());
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        Path vanillaArmorResistancesYaml = Paths.get(configPath.toString(), "armorResistances_vanilla.yaml");
        if (!Files.exists(vanillaArmorResistancesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaArmorResistancesYaml);
                String toWrite = yaml.dumpAs(database.getArmorResistanceDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        Path vanillaEntityResistancesYaml = Paths.get(configPath.toString(), "entityResistances_vanilla.yaml");
        if (!Files.exists(vanillaEntityResistancesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaEntityResistancesYaml);
                String toWrite = yaml.dumpAs(database.getEntityResistanceDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        Path vanillaEntityDamagesYaml = Paths.get(configPath.toString(), "entityDamages_vanilla.yaml");
        if (!Files.exists(vanillaEntityDamagesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaEntityDamagesYaml);
                String toWrite = yaml.dumpAs(database.getEntityDamageDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        Path vanillaToolDamagesYaml = Paths.get(configPath.toString(), "toolDamages_vanilla.yaml");
        if (!Files.exists(vanillaToolDamagesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaToolDamagesYaml);
                String toWrite = yaml.dumpAs(database.getToolDamageDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

        Path vanillaEnchantmentResistancesYaml = Paths.get(configPath.toString(), "enchantmentResistances_vanilla.yaml");
        if (!Files.exists(vanillaEnchantmentResistancesYaml)) {
            try {
                writer = Files.newBufferedWriter(vanillaEnchantmentResistancesYaml);
                String toWrite = yaml.dumpAs(database.getEnchantmentResistanceDatabase(), Tag.MAP, null);
                writer.write(toWrite);
                writer.close();
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        }

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

        try {
            reader = Files.newBufferedReader(vanillaDamageSourcesYaml);
            parseDamageSourcesYaml(yaml.loadAs(reader, DamageSources.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        try {
            reader = Files.newBufferedReader(vanillaArmorResistancesYaml);
            parseArmorResistancesYaml(yaml.loadAs(reader, ArmorResistances.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        try {
            reader = Files.newBufferedReader(vanillaEntityDamagesYaml);
            parseEntityDamagesYaml(yaml.loadAs(reader, EntityDamages.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        try {
            reader = Files.newBufferedReader(vanillaEntityResistancesYaml);
            parseEntityResistancesYaml(yaml.loadAs(reader, EntityResistances.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        try {
            reader = Files.newBufferedReader(vanillaToolDamagesYaml);
            parseToolDamagesYaml(yaml.loadAs(reader, ToolDamages.class));
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        try {
            File[] fileList = customArmorResistancesYamlPath.toFile().listFiles();
            for (File file : fileList) {
                reader = Files.newBufferedReader(file.toPath());
                parseArmorResistancesYaml(yaml.loadAs(reader, ArmorResistances.class));
            }
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        try {
            File[] fileList = customEntityResistancesYamlPath.toFile().listFiles();
            for (File file : fileList) {
                reader = Files.newBufferedReader(file.toPath());
                parseEntityResistancesYaml(yaml.loadAs(reader, EntityResistances.class));
            }
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        try {
            File[] fileList = customToolDamagesYamlPath.toFile().listFiles();
            for (File file : fileList) {
                reader = Files.newBufferedReader(file.toPath());
                parseToolDamagesYaml(yaml.loadAs(reader, ToolDamages.class));
            }
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

        try {
            File[] fileList = customEntityDamageYamlPath.toFile().listFiles();
            for (File file : fileList) {
                reader = Files.newBufferedReader(file.toPath());
                parseEntityDamagesYaml(yaml.loadAs(reader, EntityDamages.class));
            }
        } catch (IOException e) {
            DamageOverhaul.log(Level.ERROR, e.getMessage());
        }

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
                }
                else {
                    PatchouliJSONGenerator.addEntityResistancePage(entry, entityType);
                }
                resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/entries/entities/"+Registry.ENTITY_TYPE.getId(entityType).getPath()+".json"), new JsonResource(entry));

            }
            for (Item toolItem : ToolDamages.values()) {
                resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/entries/tool_damages/"+Registry.ITEM.getId(toolItem).getPath()+".json"), new JsonResource(PatchouliJSONGenerator.generateToolEntryJson(toolItem)));
            }
            for (Item armorItem : ArmorResistances.values()) {
                resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/entries/armor_resistances/"+Registry.ITEM.getId(armorItem).getPath()+".json"), new JsonResource(PatchouliJSONGenerator.generateArmorEntryJson(armorItem, ((ArmorItem)armorItem).getMaterial())));
            }
            for (Enchantment enchantment : EnchantmentResistances.values()) {
                resourcePackBuilder.add(new Identifier(DamageOverhaul.MOD_ID, "patchouli_books/guidebook/en_us/entries/enchantment_resistances/"+Registry.ENCHANTMENT.getId(enchantment).getPath()+".json"), new JsonResource(PatchouliJSONGenerator.generateEnchantmentEntryJson(enchantment)));
            }
            try {
                resourcePackBuilder.dumpResources("./resources", "data");
            } catch (IOException e) {
                DamageOverhaul.log(Level.ERROR, e.getMessage());
            }
        });
        Artifice.registerData(new Identifier(DamageOverhaul.MOD_ID, "guidebook"), dataPack);

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
                        newArmorMap.put(entry.getKey(), entry.getValue());
                    }
                    DamageOverhaul.ATTRIBUTE_DATABASE.getArmorResistanceDatabase().registerArmorResistance(armorItem, newArmorMap);
                }
                else {
                    for (String damageType : resistances.getArmorResistanceDatabase().get(id).keySet()) {
                        float resistanceDef = resistances.getArmorResistanceDatabase().get(id).get(damageType).getModifier();
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
                    for (Map.Entry<DamageType, DamageAttribute> entry : damages.getDamageSpread(entityType).entrySet()) {
                        newDamageMap.put(entry.getKey(), entry.getValue());
                    }
                    DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().registerEntityDamageSpread(entityType, newDamageMap);
                }
                else {
                    for (String damageType : damages.getEntityDamageDatabase().get(id).keySet()) {
                        float damageDef = damages.getEntityDamageDatabase().get(id).get(damageType).getModifier();
                        boolean isKeyPresent = DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).containsKey(DamageTypes.valueOf(damageType));
                        if (isKeyPresent && damageDef != DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).get(DamageTypes.valueOf(damageType)).getModifier()) {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).replace(DamageTypes.valueOf(damageType), new DamageAttribute(damageDef));
                        }
                        else {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).put(DamageTypes.valueOf(damageType), new DamageAttribute(damageDef));
                        }
                        presentDamageTypes.add(DamageTypes.valueOf(damageType));
                    }
                    damageTypes.removeAll(presentDamageTypes);
                    if (!damageTypes.isEmpty()) {
                        for (DamageType type : damageTypes) {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getEntityDamageDatabase().getDamageSpread(entityType).remove(type);
                        }
                    }
                }
            }
            else {
                DamageOverhaul.log(Level.WARN, String.format("[Entity Damages Loader] Registry does not contain entity with id: %s, skipping!", identifier.toString()));
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
                        newResistanceMap.put(DamageTypes.valueOf(entry.getKey()), entry.getValue());
                    }
                    DamageOverhaul.ATTRIBUTE_DATABASE.getEntityResistanceDatabase().registerEntityResistanceSpread(entityType, newResistanceMap);
                }
                else {
                    for (String damageType : resistances.getEntityResistanceDatabase().get(id).keySet()) {
                        DamageCondition conditionDef = resistances.getEntityResistanceDatabase().get(id).get(damageType).getDamageCondition();
                        Float resistanceAmount = resistances.getEntityResistanceDatabase().get(id).get(damageType).getModifier();
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
                DamageOverhaul.log(Level.WARN, String.format("[Entity Resistances Loader] Registry does not contain entity with id: %s, skipping!", identifier.toString()));
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
                    Map<DamageType, DamageAttribute> newArmorMap = new HashMap<>();
                    for (Map.Entry<DamageType, DamageAttribute> entry : damages.getDamageSpread(toolItem).entrySet()) {
                        newArmorMap.put(entry.getKey(), entry.getValue());
                    }
                    DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().registerToolDamageSpread(toolItem, newArmorMap);
                }
                else {
                    for (String damageType : damages.getToolDamageDatabase().get(id).keySet()) {
                        float resistanceDef = damages.getDamageSpread(toolItem).get(DamageTypes.valueOf(damageType)).getModifier();
                        boolean isKeyPresent = DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).containsKey(DamageTypes.valueOf(damageType));
                        if (isKeyPresent && resistanceDef != DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).get(DamageTypes.valueOf(damageType)).getModifier()) {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).replace(DamageTypes.valueOf(damageType), new DamageAttribute(resistanceDef));
                        }
                        else {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).put(DamageTypes.valueOf(damageType), new DamageAttribute(resistanceDef));
                        }
                        presentDamageTypes.add(DamageTypes.valueOf(damageType));
                    }
                    damageTypes.removeAll(presentDamageTypes);
                    if (!damageTypes.isEmpty()) {
                        for (DamageType type : damageTypes) {
                            DamageOverhaul.ATTRIBUTE_DATABASE.getToolDamageDatabase().getDamageSpread(toolItem).remove(type);
                        }
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
                for (String damageType : sources.getDamageSourceDatabase().get(source.getName()).keySet()) {
                    float damageDef = sources.getDamageSourceDatabase().get(source.getName()).get(damageType).getModifier();
                    boolean isKeyPresent = DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).containsKey(DamageTypes.valueOf(damageType));
                    if (isKeyPresent && damageDef != DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).get(DamageTypes.valueOf(damageType)).getModifier()) {
                        DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).replace(DamageTypes.valueOf(damageType), new DamageAttribute(damageDef));
                    }
                    else {
                        DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).put(DamageTypes.valueOf(damageType), new DamageAttribute(damageDef));
                    }
                    presentDamageTypes.add(DamageTypes.valueOf(damageType));
                }
                damageTypes.removeAll(presentDamageTypes);
                if (!damageTypes.isEmpty()) {
                    for (DamageType type : damageTypes) {
                        DamageOverhaul.ATTRIBUTE_DATABASE.getDamageSourceDatabase().getDamageSpread(source).remove(type);
                    }
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
}
