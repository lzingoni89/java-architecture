package com.violeta.javaarchitecture.config.rulesets.eventfired;

import com.violeta.javaarchitecture.config.persistance.base.EntityType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class EventContext {

    private @Getter EntityType entity;
    private @Getter String event;

}
