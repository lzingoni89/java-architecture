package com.violeta.javaarchitecture.domain.rulesets.eventfired.sendemail;

import com.violeta.javaarchitecture.config.rulesets.eventfired.EventContext;
import com.violeta.javaarchitecture.config.rulesets.eventfired.EventFired;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EF01200Address extends EventFired {
    @Override
    protected String parentRule() {
        return "EF01000SendEmail";
    }

    @Override
    public boolean condition(EventContext eventContext) {
        log.error("EF01200Address::condition - No es del tipo Address");
        return false;
    }

    @Override
    public void action(EventContext eventContext) {

    }
}
