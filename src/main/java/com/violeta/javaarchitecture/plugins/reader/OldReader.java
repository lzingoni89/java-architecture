package com.violeta.javaarchitecture.plugins.reader;

import com.violeta.javaarchitecture.config.plugins.registry.ReaderPlugin;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class OldReader extends ReaderPlugin {
    @Override
    protected String setSubType() {
        return "old";
    }

    @Override
    public void read() {
        log.info("OldReader::read - OLD");
    }

}
