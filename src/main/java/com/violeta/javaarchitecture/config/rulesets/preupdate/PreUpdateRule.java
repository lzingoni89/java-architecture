package com.violeta.javaarchitecture.config.rulesets.preupdate;

import com.violeta.javaarchitecture.config.persistance.base.EntityType;
import org.springframework.plugin.core.Plugin;

public abstract class PreUpdateRule implements Plugin<String> {

    private final String parentRule;

    public PreUpdateRule() {
        this.parentRule = parentRule();
    }

    @Override
    public boolean supports(String className) {
        if ((className == null || className.isEmpty())
                && (this.parentRule == null || this.parentRule.isEmpty())) {
            return true;
        }
        return this.parentRule != null && this.parentRule.equalsIgnoreCase(className);
    }

    /**
     * Return null if you want this rule as a Root Rule
     *
     * @return parent rule name
     */
    protected abstract String parentRule();

    public abstract boolean condition(EntityType entity);

    public abstract void action(EntityType entity);

}
