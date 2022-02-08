package com.violeta.javaarchitecture.config.plugins.registry;

import com.violeta.javaarchitecture.config.plugins.PluginBase;
import com.violeta.javaarchitecture.config.plugins.PluginTypeList;

public abstract class WriterPlugin extends PluginBase {

    public WriterPlugin() {
        super(PluginTypeList.WriterPlugin);
    }

    @Override
    protected String setSubType() {
        return null;
    }

    public abstract void write(String message);

}
