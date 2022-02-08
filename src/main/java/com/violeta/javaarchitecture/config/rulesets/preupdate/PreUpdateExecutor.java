package com.violeta.javaarchitecture.config.rulesets.preupdate;

import com.violeta.javaarchitecture.config.persistance.base.EntityType;
import com.violeta.javaarchitecture.config.rulesets.eventfired.EventContext;
import com.violeta.javaarchitecture.config.rulesets.eventfired.EventFired;
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
public class PreUpdateExecutor {

    @Value("${application.rulesets.preupdate.rootPath}")
    private static String PREUPDATE_ROOT_PATH;
    private static final String ROOT = "";
    private static final PluginRegistry<PreUpdateRule, String> preUpdateRegistry = loadRegistry();

    private static PluginRegistry<PreUpdateRule, String> loadRegistry() {
        var reflections = new Reflections(PREUPDATE_ROOT_PATH);
        var eventFiredList = reflections
                .getSubTypesOf(PreUpdateRule.class)
                .stream()
                .sorted(Comparator.comparing(Class::getSimpleName))
                .collect(Collectors.toList());
        return SimplePluginRegistry.of(eventFiredList
                .stream()
                .map(aClass -> {
                    try {
                        return (PreUpdateRule) aClass.getConstructor().newInstance();
                    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                        log.error("EventFiredExecutor::loadRegistry - Getting EventFired Rules from " + PREUPDATE_ROOT_PATH, e);
                        return null;
                    }
                }).collect(Collectors.toList()));
    }

    public static void execute(EntityType entity) {
        if (entity == null) {
            return;
        }
        var eventFiredRoots = preUpdateRegistry.getPluginsFor(ROOT);
        processEventFiredList(eventFiredRoots, entity);
    }

    private static void processEventFiredList(List<PreUpdateRule> preUpdateList, EntityType entity) {
        if (preUpdateList == null || preUpdateList.isEmpty()) {
            return;
        }
        for (PreUpdateRule preUpdateRule : preUpdateList) {
            if (!preUpdateRule.condition(entity)) {
                continue;
            }
            preUpdateRule.action(entity);
            var preUpdateChildren = preUpdateRegistry.getPluginsFor(preUpdateRule.getClass().getSimpleName());
            processEventFiredList(preUpdateChildren, entity);
        }
    }

}
