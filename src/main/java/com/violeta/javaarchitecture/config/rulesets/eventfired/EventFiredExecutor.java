package com.violeta.javaarchitecture.config.rulesets.eventfired;

import lombok.extern.log4j.Log4j2;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.plugin.core.SimplePluginRegistry;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class EventFiredExecutor {

    @Value("${application.rulesets.eventfired.rootPath}")
    private static String EVENT_FIRED_ROOT_PATH;
    private static final String ROOT = "";
    private static final PluginRegistry<EventFired, String> eventFiredRegistry = loadRegistry();

    private static PluginRegistry<EventFired, String> loadRegistry() {
        var reflections = new Reflections(EVENT_FIRED_ROOT_PATH);
        var eventFiredList = reflections
                .getSubTypesOf(EventFired.class)
                .stream()
                .sorted(Comparator.comparing(Class::getSimpleName))
                .collect(Collectors.toList());
        return SimplePluginRegistry.of(eventFiredList
                .stream()
                .map(aClass -> {
                    try {
                        return (EventFired) aClass.getConstructor().newInstance();
                    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                        log.error("EventFiredExecutor::loadRegistry - Getting EventFired Rules from " + EVENT_FIRED_ROOT_PATH, e);
                        return null;
                    }
                }).collect(Collectors.toList()));
    }

    public static void execute(EventContext eventContext) {
        if (eventContext == null) {
            return;
        }
        var eventFiredRoots = eventFiredRegistry.getPluginsFor(ROOT);
        processEventFiredList(eventFiredRoots, eventContext);
    }

    private static void processEventFiredList(List<EventFired> eventFiredList, EventContext eventContext) {
        if (eventFiredList == null || eventFiredList.isEmpty()) {
            return;
        }
        for (EventFired eventFired : eventFiredList) {
            if (!eventFired.condition(eventContext)) {
                continue;
            }
            eventFired.action(eventContext);
            var eventFiredChildren = eventFiredRegistry.getPluginsFor(eventFired.getClass().getSimpleName());
            processEventFiredList(eventFiredChildren, eventContext);
        }
    }

}
