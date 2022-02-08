package com.violeta.javaarchitecture;

import com.violeta.javaarchitecture.config.plugins.PluginFinder;
import com.violeta.javaarchitecture.config.plugins.PluginTypeList;
import com.violeta.javaarchitecture.config.plugins.registry.ReaderPlugin;
import com.violeta.javaarchitecture.config.plugins.registry.WriterPlugin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class JavaArchitectureApplicationTests {

    @Autowired
    PluginFinder pluginFinder;

    @Test
    void writerPluginFound() {
        var saabeee = (WriterPlugin) pluginFinder.find(PluginTypeList.WriterPlugin);
        Assert.notNull(saabeee, "Plugin recuperado " + (saabeee.getClass().getSimpleName()));
        saabeee.write(null);
    }

    @Test
    void noPluginFound() {
        var saabeee = (ReaderPlugin) pluginFinder.find(PluginTypeList.ReaderPlugin);
        Assert.isNull(saabeee, "Plugin no recuperado");
    }

    @Test
    void readerPluginFound() {
        var saabeee = (ReaderPlugin) pluginFinder.find(PluginTypeList.ReaderPlugin, "new");
        Assert.notNull(saabeee, "Plugin recuperado " + (saabeee.getClass().getSimpleName()));
        saabeee.read();
    }

}
