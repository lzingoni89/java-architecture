package com.violeta.javaarchitecture.config.plugins;

import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class PluginFinder {

    private final PluginRegistry<PluginBase, String> pluginRegistry;

    public PluginFinder(PluginRegistry<PluginBase, String> pluginRegistry) {
        this.pluginRegistry = pluginRegistry;
    }

    public PluginBase find(PluginTypeList pluginType) {
        return find(pluginType, null);
    }

    public PluginBase find(PluginTypeList pluginType, String subtype) {
        if (pluginType == null) return null;
        var pluginId = PluginsUtil.createPluginId(pluginType, subtype);
        var pluginResult = this.pluginRegistry.getPluginFor(pluginId);
        if (pluginResult.isEmpty()) {
            pluginResult = this.pluginRegistry
                    .getPlugins().stream()
                    .filter(plugin -> plugin.supportsWithoutProfileValidation(pluginId))
                    .findFirst();
        }
        return pluginResult.orElse(null);
    }

}
