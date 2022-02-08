package com.violeta.javaarchitecture.domain.rulesets.eventfired.dispatchdocument;

import com.violeta.javaarchitecture.config.rulesets.eventfired.EventContext;
import com.violeta.javaarchitecture.config.rulesets.eventfired.EventFired;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EF02000DispatchDocument extends EventFired {
    @Override
    protected String parentRule() {
        return null;
    }

    @Override
    public boolean condition(EventContext eventContext) {
        log.info("EF02000DispatchDocument::condition - Evento no mapeado");
        return false;
    }

    @Override
    public void action(EventContext eventContext) {

    }

}
