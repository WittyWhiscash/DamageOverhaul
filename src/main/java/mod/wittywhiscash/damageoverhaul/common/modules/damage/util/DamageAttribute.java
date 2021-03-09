package mod.wittywhiscash.damageoverhaul.common.modules.damage.util;

import mod.wittywhiscash.damageoverhaul.api.DamageCondition;

public class DamageAttribute {


    private DamageCondition damageCondition;
    private Float modifier;

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

    public DamageCondition getDamageCondition() { return damageCondition; }
    public void setDamageCondition(DamageCondition condition) { this.damageCondition = condition; }

    public Float getModifier() { return modifier; }
    public void setModifier(Float modifier) { this.modifier = modifier; }
}
