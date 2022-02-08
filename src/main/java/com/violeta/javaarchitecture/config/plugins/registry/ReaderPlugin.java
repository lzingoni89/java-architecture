package com.violeta.javaarchitecture.config.plugins.registry;

import com.violeta.javaarchitecture.config.plugins.PluginBase;
import com.violeta.javaarchitecture.config.plugins.PluginTypeList;

public abstract class ReaderPlugin extends PluginBase {

    public ReaderPlugin() {
        super(PluginTypeList.ReaderPlugin);
    }

    public abstract void read();

}
