package com.violeta.javaarchitecture.config.plugins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.plugin.core.Plugin;

public abstract class PluginBase implements Plugin<String> {

    @Autowired
    private Environment env;

    private final PluginTypeList pluginType;
    private final String subtype;
    private final String pluginId;
    private final Profiles profiles;
    private final boolean active;

    public PluginBase(PluginTypeList pluginType) {
        if (pluginType == null) {
            throw new IllegalArgumentException("PluginType cannot be null");
        }
        this.pluginType = pluginType;
        this.subtype = setSubType();
        this.pluginId = PluginsUtil.createPluginId(this.pluginType, this.subtype);
        this.active = isActive();
        this.profiles = setProfiles();
    }

    @Override
    public boolean supports(String pluginId) {
        return this.active
                && pluginId != null
                && this.pluginId.equalsIgnoreCase(pluginId)
                && validateProfile();
    }

    public boolean supportsWithoutProfileValidation(String pluginId) {
        return this.active
                && pluginId != null
                && this.pluginId.equalsIgnoreCase(pluginId);
    }

    protected abstract String setSubType();

    protected boolean isActive() {
        return true;
    }

    protected String[] validProfiles() {
        return null;
    }

    private Profiles setProfiles() {
        var profiles = validProfiles();
        if (profiles == null || profiles.length == 0) {
            return null;
        }
        return Profiles.of(profiles);
    }

    private boolean validateProfile() {
        return this.profiles == null || env.acceptsProfiles(this.profiles);
    }

}
