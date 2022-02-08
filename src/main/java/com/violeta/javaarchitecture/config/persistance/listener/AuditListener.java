package com.violeta.javaarchitecture.config.persistance.listener;

import com.violeta.javaarchitecture.config.persistance.base.EntityType;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.Instant;

public class AuditListener {

    @PrePersist
    public void prePersistEntity(EntityType entity) {
        entity.setCreatedDate(Instant.now());
        entity.sendEvent(Events.Persist);
    }

    @PreUpdate
    public void preUpdateEntity(EntityType entity) {
        entity.setLastModifiedDate(Instant.now());
        entity.sendEvent(Events.Update);
    }

    @PreRemove
    public void preRemoveEntity(EntityType entity) {
        entity.setLastModifiedDate(Instant.now());
        entity.sendEvent(Events.Remove);
    }

}
