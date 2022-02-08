package com.violeta.javaarchitecture.config.rulesets.eventfired;

import org.springframework.plugin.core.Plugin;

public abstract class EventFired implements Plugin<String> {

    private final String parentRule;

    public EventFired() {
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

    public abstract boolean condition(EventContext eventContext);

    public abstract void action(EventContext eventContext);

}
