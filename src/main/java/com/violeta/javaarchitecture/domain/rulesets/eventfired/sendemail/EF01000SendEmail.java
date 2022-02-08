package com.violeta.javaarchitecture.domain.rulesets.eventfired.sendemail;

import com.violeta.javaarchitecture.config.rulesets.eventfired.EventContext;
import com.violeta.javaarchitecture.config.rulesets.eventfired.EventFired;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EF01000SendEmail extends EventFired {

    @Override
    protected String parentRule() {
        return null;
    }

    @Override
    public boolean condition(EventContext eventContext) {
        log.info("EF01000SendEmail::condition - " + eventContext.getEvent());
        return true;
    }

    @Override
    public void action(EventContext eventContext) {
        log.info("EF01000SendEmail::action - " + eventContext.getEvent());
    }
}
