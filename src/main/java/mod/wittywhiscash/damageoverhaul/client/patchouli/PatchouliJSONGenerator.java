package mod.wittywhiscash.damageoverhaul.client.patchouli;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import mod.wittywhiscash.damageoverhaul.DamageOverhaul;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.registry.Registry;

public class PatchouliJSONGenerator {

    public static JsonElement generateEntityEntry(EntityType<?> entityType) {
        JsonElement root = new JsonObject();
        JsonElement name = new JsonPrimitive(I18n.translate(entityType.getTranslationKey()));
        root.getAsJsonObject().add("name", name);
        JsonElement icon = new JsonPrimitive(Registry.ITEM.getId(SpawnEggItem.forEntity(entityType)).toString());
        root.getAsJsonObject().add("icon", icon);
        JsonElement category = new JsonPrimitive("entities");
        root.getAsJsonObject().add("category", category);
        JsonElement flags = new JsonPrimitive(DamageOverhaul.MOD_ID + ":" + Registry.ENTITY_TYPE.getId(entityType).getPath());
        root.getAsJsonObject().add("flag", flags);
        JsonElement pages = new JsonArray();
        root.getAsJsonObject().add("pages", pages);
        return root;
    }

    public static JsonElement addEntityLandingPage(JsonElement root, EntityType<?> entityType) {
        JsonElement pages = root.getAsJsonObject().getAsJsonArray("pages");
        JsonElement entry = new JsonObject();
        JsonElement pageType = new JsonPrimitive("entity_landing");
        entry.getAsJsonObject().add("type", pageType);
        JsonElement entity = new JsonPrimitive(Registry.ENTITY_TYPE.getId(entityType).toString());
        entry.getAsJsonObject().add("entity", entity);
        pages.getAsJsonArray().add(entry);
        return root;
    }

    public static JsonElement addEntityResistancePage(JsonElement root, EntityType<?> entityType) {
        JsonElement pages = root.getAsJsonObject().getAsJsonArray("pages");
        JsonElement entry = new JsonObject();
        JsonElement pageType = new JsonPrimitive("entity_resistance");
        entry.getAsJsonObject().add("type", pageType);
        JsonElement entity = new JsonPrimitive(Registry.ENTITY_TYPE.getId(entityType).toString());
        entry.getAsJsonObject().add("entity", entity);
        pages.getAsJsonArray().add(entry);
        return root;
    }

    public static JsonElement addEntityDamagePage(JsonElement root, EntityType<?> entityType) {
        JsonElement pages = root.getAsJsonObject().getAsJsonArray("pages");
        JsonElement entry = new JsonObject();
        JsonElement pageType = new JsonPrimitive("entity_damage");
        entry.getAsJsonObject().add("type", pageType);
        JsonElement entity = new JsonPrimitive(Registry.ENTITY_TYPE.getId(entityType).toString());
        entry.getAsJsonObject().add("entity", entity);
        pages.getAsJsonArray().add(entry);
        return root;
    }

    public static JsonElement generateToolEntryJson(Item tool) {
        JsonElement root = new JsonObject();
        JsonElement name = new JsonPrimitive(I18n.translate(tool.getTranslationKey()));
        root.getAsJsonObject().add("name", name);
        JsonElement icon = new JsonPrimitive(Registry.ITEM.getId(tool).toString());
        root.getAsJsonObject().add("icon", icon);
        JsonElement category = new JsonPrimitive("tool_damages");
        root.getAsJsonObject().add("category", category);
        JsonElement flags = new JsonPrimitive(DamageOverhaul.MOD_ID + ":" + Registry.ITEM.getId(tool).getPath());
        root.getAsJsonObject().add("flag", flags);
        JsonElement pages = new JsonArray();
        JsonElement entry = new JsonObject();
        JsonElement entryType = new JsonPrimitive("tool_damage");
        entry.getAsJsonObject().add("type", entryType);
        JsonElement toolItem = new JsonPrimitive(Registry.ITEM.getId(tool).toString());
        entry.getAsJsonObject().add("item", toolItem);
        pages.getAsJsonArray().add(entry);
        root.getAsJsonObject().add("pages", pages);
        return root;
    }

    public static JsonElement generateArmorEntryJson(Item armor, ArmorMaterial material) {
        JsonElement root = new JsonObject();
        JsonElement name = new JsonPrimitive(I18n.translate(armor.getTranslationKey()));
        root.getAsJsonObject().add("name", name);
        JsonElement icon = new JsonPrimitive(Registry.ITEM.getId(armor).toString());
        root.getAsJsonObject().add("icon", icon);
        JsonElement category = new JsonPrimitive("armor_resistances");
        root.getAsJsonObject().add("category", category);
        JsonElement flags = new JsonPrimitive(DamageOverhaul.MOD_ID + ":" + Registry.ITEM.getId(armor).getPath());
        root.getAsJsonObject().add("flag", flags);
        JsonElement pages = new JsonArray();
        JsonElement entry = new JsonObject();
        JsonElement entryType = new JsonPrimitive("armor_resistance");
        entry.getAsJsonObject().add("type", entryType);
        JsonElement armorItem = new JsonPrimitive(Registry.ITEM.getId(armor).toString());
        entry.getAsJsonObject().add("item", armorItem);
        pages.getAsJsonArray().add(entry);
        root.getAsJsonObject().add("pages", pages);
        return root;
    }

    public static JsonElement generateEnchantmentEntryJson(Enchantment enchantmentToType) {
        JsonElement root = new JsonObject();
        JsonElement name = new JsonPrimitive(I18n.translate(enchantmentToType.getTranslationKey()));
        root.getAsJsonObject().add("name", name);
        JsonElement icon = new JsonPrimitive(Registry.ITEM.getId(Items.ENCHANTED_BOOK).toString());
        root.getAsJsonObject().add("icon", icon);
        JsonElement category = new JsonPrimitive("enchantment_resistances");
        root.getAsJsonObject().add("category", category);
        JsonElement flags = new JsonPrimitive(DamageOverhaul.MOD_ID + ":" + Registry.ENCHANTMENT.getId(enchantmentToType).getPath());
        root.getAsJsonObject().add("flag", flags);
        JsonElement pages = new JsonArray();
        JsonElement entry = new JsonObject();
        JsonElement entryType = new JsonPrimitive("enchantment_resistance");
        entry.getAsJsonObject().add("type", entryType);
        JsonElement enchantment = new JsonPrimitive(Registry.ENCHANTMENT.getId(enchantmentToType).toString());
        entry.getAsJsonObject().add("enchantment", enchantment);
        pages.getAsJsonArray().add(entry);
        root.getAsJsonObject().add("pages", pages);
        return root;
    }

    public static JsonElement generateCategory(String categoryName, Item iconItem) {
        JsonElement root = new JsonObject();
        JsonElement name = new JsonPrimitive(categoryName);
        root.getAsJsonObject().add("name", name);
        JsonElement description = new JsonPrimitive("Description.");
        root.getAsJsonObject().add("description", description);
        JsonElement icon = new JsonPrimitive(Registry.ITEM.getId(iconItem).toString());
        root.getAsJsonObject().add("icon", icon);
        return root;
    }

    public static JsonElement generateRootBookJson() {
        JsonElement root = new JsonObject();
        JsonElement bookExtends = new JsonPrimitive("damageoverhaul:guidebook");
        root.getAsJsonObject().add("extend", bookExtends);
        return root;
    }

}
