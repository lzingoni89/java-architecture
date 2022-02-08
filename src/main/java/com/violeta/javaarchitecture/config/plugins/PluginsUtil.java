package com.violeta.javaarchitecture.config.plugins;

public class PluginsUtil {

    public static String createPluginId(PluginTypeList pluginType, String subtype) {
        if (subtype == null || subtype.isEmpty()) {
            return pluginType.toString();
        }
        return pluginType.toString() + "|" + subtype;
    }

}
