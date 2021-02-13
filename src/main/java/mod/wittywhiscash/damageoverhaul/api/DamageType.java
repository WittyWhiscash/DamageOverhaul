package mod.wittywhiscash.damageoverhaul.api;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Map;

public class DamageType {

    @Expose
    private final String registryName;

    public static final String BLUDGEONING = "BLUDGEONING";
    public static final String SLASHING = "SLASHING";
    public static final String PIERCING = "PIERCING";
    public static final String MAGIC = "MAGIC";
    public static final String FIRE = "FIRE";
    public static final String WITHER = "WITHER";

    public DamageType(String type) {
        this.registryName = type.toLowerCase();
    }

    public String getRegistryName() {
        return registryName;
    }

    @Override
    public String toString() {
        return getRegistryName();
    }
}
