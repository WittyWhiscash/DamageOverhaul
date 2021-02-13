package mod.wittywhiscash.damageoverhaul.common.damage;

import mod.wittywhiscash.damageoverhaul.api.DamageCondition;

import java.util.Optional;

public class DamageAttribute {


    private final DamageCondition damageCondition;
    private final Float modifier;

    public DamageAttribute() {
        this.damageCondition = null;
        this.modifier = null;
    }

    public DamageAttribute(DamageCondition condition, Float mod) {
        this.damageCondition = condition;
        this.modifier = mod;
    }

    public DamageAttribute(DamageCondition condition) {
        this.damageCondition = condition;
        this.modifier = null;
    }

    public DamageAttribute(Float mod) {
        this.damageCondition = null;
        this.modifier = mod;
    }

    public DamageCondition getDamageCondition() {
        return damageCondition;
    }

    public Float getModifier() {
        return modifier;
    }
}
