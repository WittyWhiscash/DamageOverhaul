package mod.wittywhiscash.damageoverhaul.common.modules.damage.database.defaults;

import mod.wittywhiscash.damageoverhaul.api.DamageType;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.util.DamageAttribute;
import mod.wittywhiscash.damageoverhaul.common.modules.damage.database.DamageTypes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.LinkedHashMap;
import java.util.Map;

public enum DefaultArmorResistances {
    /*
        # Leather armor is mostly just a padding, so that's bludegeoning
        # resistance. Other than that, slashing and piercing tear through
        # it pretty well. Piercing a little less so, because hard hide is
        # rather difficult to puncture.
        -Csillagvihar
    */
    LEATHER_HELMET(Items.LEATHER_HELMET, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    LEATHER_CHESTPLATE(Items.LEATHER_CHESTPLATE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    LEATHER_LEGGINGS(Items.LEATHER_LEGGINGS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    LEATHER_BOOTS(Items.LEATHER_BOOTS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    /*
        # Chainmail armor was created to protect against slashing attacks,
        # and as such should be its main strength. Piercing attacks are a
        # bit more effective, and bludgeoning is only lessened by the
        # inertial mass of the armor, which is not much, so that's
        # definitely a very weak point.
        -Csillagvihar
    */
    CHAIN_HELMET(Items.CHAINMAIL_HELMET, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    CHAIN_CHESTPLATE(Items.CHAINMAIL_CHESTPLATE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(5.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(4.0F));
    }}),
    CHAIN_LEGGINGS(Items.CHAINMAIL_LEGGINGS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(4.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(3.0F));
    }}),
    CHAIN_BOOTS(Items.CHAINMAIL_BOOTS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
    }}),
    /*
        # So iron armor is plate armor, which is perfect against slashing, is
        # better against piercing than chain, and bludgeoning is lessened by
        # the structural strength of the armor, as well as the (usually)
        # underlying padding, but is still a weak point. Also, as an added
        # bonus, full-body armors like this should offer some semblance of
        # protection against heat, so I sprinkled in a tiny bit of fire
        # protection.
        -Csillagvihar
    */
    IRON_HELMET(Items.IRON_HELMET, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(1.0F));
    }}),
    IRON_CHESTPLATE(Items.IRON_CHESTPLATE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(6.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(5.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(2.0F));
    }}),
    IRON_LEGGINGS(Items.IRON_LEGGINGS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(5.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(4.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(1.0F));
    }}),
    IRON_BOOTS(Items.IRON_BOOTS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(1.0F));
    }}),
    /*
        # So the thing with golden armor is that it shouldn't exist, but since
        # it does, may as well make it useful in a way, and because canonically
        # gold is easy to enchant that means it should absorb *magic* pretty
        # well. That's the reason for the magic protection.
        # It's also a full-body armor but since gold conducts heat so well, no
        # bueno on the fire protection. On the other hand, since it deforms so
        # well, it should absorb more of the blow of bludgeoning strikes,
        # overall creating a not too shabby piece of armor set. If only it didn't
        # break so quickly.
        -Csillagvihar
    */
    GOLD_HELMET(Items.GOLDEN_HELMET, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(2.0F));
    }}),
    GOLD_CHESTPLATE(Items.GOLDEN_CHESTPLATE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(5.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(4.0F));
    }}),
    GOLD_LEGGINGS(Items.GOLDEN_LEGGINGS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(2.0F));
    }}),
    GOLD_BOOTS(Items.GOLDEN_BOOTS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(1.0F));
    }}),
    /*
        # Diamond is boring, since it's just a better iron armor. Or it would,
        # if I didn't decide that because of its brittle nature, bludgeoning
        # and piercing wouldn't be its strong points! Slashing damage is the
        # weakest kind of damage against armored opponents it would seem.
        # Also protects against fire better than iron.
        -Csillagvihar
    */
    DIAMOND_HELMET(Items.DIAMOND_HELMET, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(1.0F));
    }}),
    DIAMOND_CHESTPLATE(Items.DIAMOND_CHESTPLATE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(5.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(8.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(6.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(3.0F));
    }}),
    DIAMOND_LEGGINGS(Items.DIAMOND_LEGGINGS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(6.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(4.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(2.0F));
    }}),
    DIAMOND_BOOTS(Items.DIAMOND_BOOTS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(1.0F));
    }}),
    /*
        # And now netherite. I'd say the ancient metal pieces give it a bit of
        # an edge against wither, and the gold used in those ingots absorbs some
        # of the magic damage, though not as well as full golden armor, along
        # with the added benefit of better protection against the weak points
        # of diamond - piercing and bludgeoning. Also protects against fire even
        # better than diamond.
        -Csillagvihar
    */
    NETHERITE_HELMET(Items.NETHERITE_HELMET, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(1.0F));
    }}),
    NETHERITE_CHESTPLATE(Items.NETHERITE_CHESTPLATE, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(6.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(8.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(7.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(4.0F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(2.0F));
    }}),
    NETHERITE_LEGGINGS(Items.NETHERITE_LEGGINGS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(4.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(6.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(5.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(1.0F));
    }}),
    NETHERITE_BOOTS(Items.NETHERITE_BOOTS, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(3.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.MAGIC), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.WITHER), new DamageAttribute(1.0F));
    }}),
    /*
        # And then there's the turtle helmet. It's basically iron, with a bit
        # better fire protection.
        -Csillagvihar
    */
    TURTLE_HELMET(Items.TURTLE_HELMET, new LinkedHashMap<DamageType, DamageAttribute>(){{
        put(DamageTypes.valueOf(DamageType.BLUDGEONING), new DamageAttribute(1.0F));
        put(DamageTypes.valueOf(DamageType.SLASHING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.PIERCING), new DamageAttribute(2.0F));
        put(DamageTypes.valueOf(DamageType.FIRE), new DamageAttribute(2.0F));
    }}),
    ;

    private final Item armor;
    private final Map<DamageType, DamageAttribute> modifierSpread;

    DefaultArmorResistances(Item armorItem, Map<DamageType, DamageAttribute> modifierArray) {
        this.armor = armorItem;
        this.modifierSpread = modifierArray;
    }

    public Item getArmor() {
        return armor;
    }

    public Map<DamageType, DamageAttribute> getModifierSpread() {
        return modifierSpread;
    }
}
