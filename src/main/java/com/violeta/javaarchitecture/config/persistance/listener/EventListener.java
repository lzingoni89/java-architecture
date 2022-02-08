package com.violeta.javaarchitecture.config.persistance.listener;

import com.violeta.javaarchitecture.config.persistance.base.EntityType;
import com.violeta.javaarchitecture.config.rulesets.eventfired.EventContext;
import com.violeta.javaarchitecture.config.rulesets.eventfired.EventFiredExecutor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Log4j2
public class EventListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    private void processEvent(EntityType entity) {
        for (String event : entity.getEvents()) {
            EventFiredExecutor.execute(new EventContext(entity, event));
        }
    }

}
