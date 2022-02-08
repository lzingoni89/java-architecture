package com.violeta.javaarchitecture.config.persistance.base;

import com.violeta.javaarchitecture.config.persistance.listener.AuditListener;
import com.violeta.javaarchitecture.config.persistance.listener.EventListener;
import com.violeta.javaarchitecture.config.persistance.listener.Events;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


@MappedSuperclass
@EntityListeners({AuditListener.class, EventListener.class})
public abstract class EntityType {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Getter
    @Setter
    Long id;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private @Getter
    @Setter
    Instant createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private @Getter
    @Setter
    Instant lastModifiedDate;

    @Transient
    private @Getter
    Set<String> events;

    public void sendEvent(Events event) {
        if (event == null) {
            return;
        }
        if (this.events == null) {
            events = new HashSet<>();
        }
        this.events.add(getClass().getSimpleName() + ":" + event);
    }

}
