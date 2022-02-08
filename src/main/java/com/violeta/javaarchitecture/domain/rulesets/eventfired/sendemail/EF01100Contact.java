package com.violeta.javaarchitecture.domain.rulesets.eventfired.sendemail;

import com.violeta.javaarchitecture.config.rulesets.eventfired.EventContext;
import com.violeta.javaarchitecture.config.rulesets.eventfired.EventFired;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EF01100Contact extends EventFired {
    @Override
    protected String parentRule() {
        return "EF01000SendEmail";
    }

    @Override
    public boolean condition(EventContext eventContext) {
        log.info("EF01100Contact::condition - Es del tipo Contact");
        return true;
    }

    @Override
    public void action(EventContext eventContext) {
        log.info("EF01100Contact::action - Sending Message");
    }
}
