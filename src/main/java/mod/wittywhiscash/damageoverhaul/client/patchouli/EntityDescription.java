package mod.wittywhiscash.damageoverhaul.client.patchouli;

public enum EntityDescription {
    BAT("book.damageoverhaul.bat.sumBlock", "book.damageoverhaul.bat.descBlock"),
    BEE("book.damageoverhaul.bee.sumBlock", "book.damageoverhaul.bee.descBlock"),
    BLAZE("book.damageoverhaul.blaze.sumBlock", "book.damageoverhaul.blaze.descBlock"),
    CAT("", ""),
    CAVE_SPIDER("", ""),
    CHICKEN("", ""),
    COD("", ""),
    COW("", ""),
    CREEPER("", ""),
    DOLPHIN("", ""),
    DONKEY("", ""),
    DROWNED("", ""),
    ELDER_GUARDIAN("", ""),
    ENDER_DRAGON("", ""),
    ENDERMAN("", ""),
    ENDERMITE("", ""),
    EVOKER("", ""),
    FOX("", ""),
    GHAST("", ""),
    GIANT("", ""),
    GUARDIAN("", ""),
    HOGLIN("", ""),
    HORSE("", ""),
    HUSK("", ""),
    ILLUSIONER("", ""),
    IRON_GOLEM("", ""),
    LLAMA("", ""),
    MAGMA_CUBE("", ""),
    MULE("", ""),
    MOOSHROOM("", ""),
    OCELOT("", ""),
    PANDA("", ""),
    PARROT("", ""),
    PHANTOM("", ""),
    PIG("", ""),
    PIGLIN("", ""),
    PIGLIN_BRUTE("", ""),
    PILLAGER("", ""),
    PLAYER("", ""),
    POLAR_BEAR("", ""),
    PUFFERFISH("", ""),
    RABBIT("", ""),
    RAVAGER("", ""),
    SALMON("", ""),
    SHEEP("", ""),
    SHULKER("", ""),
    SILVERFISH("", ""),
    SKELETON("", ""),
    SKELETON_HORSE("", ""),
    SLIME("", ""),
    SNOW_GOLEM("", ""),
    SPIDER("", ""),
    SQUID("", ""),
    STRAY("", ""),
    STRIDER("", ""),
    TRADER_LLAMA("", ""),
    TROPICAL_FISH("", ""),
    TURTLE("", ""),
    VEX("", ""),
    VILLAGER("", ""),
    VINDICATOR("", ""),
    WANDERING_TRADER("", ""),
    WITCH("", ""),
    WITHER("", ""),
    WITHER_SKELETON("", ""),
    WOLF("", ""),
    ZOGLIN("", ""),
    ZOMBIE("", ""),
    ZOMBIE_HORSE("", ""),
    ZOMBIE_VILLAGER("", ""),
    ZOMBIFIED_PIGLIN("", "");

    private final String summaryBlock;
    private final String descriptionBlock;

    EntityDescription(String sumBlock, String descBlock) {
        this.summaryBlock = sumBlock;
        this.descriptionBlock = descBlock;
    }

    public String getSummaryBlock() {
        return summaryBlock;
    }

    public String getDescriptionBlock() {
        return descriptionBlock;
    }
}
