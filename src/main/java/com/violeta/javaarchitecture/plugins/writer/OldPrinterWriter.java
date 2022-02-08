package com.violeta.javaarchitecture.plugins.writer;

import com.violeta.javaarchitecture.config.plugins.registry.WriterPlugin;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class OldPrinterWriter extends WriterPlugin {

    @Override
    public void write(String message) {
        log.info("OldPrinterWriter::write - Old printer");
    }

    @Override
    protected String[] validProfiles() {
        return new String[]{"dev"};
    }

}
