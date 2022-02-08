package com.violeta.javaarchitecture.plugins.reader;

import com.violeta.javaarchitecture.config.plugins.registry.ReaderPlugin;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class NewReader extends ReaderPlugin {

    @Override
    protected String setSubType() {
        return "new";
    }

    @Override
    public void read() {
        log.info("NewReader::read - NEW");
    }

}
