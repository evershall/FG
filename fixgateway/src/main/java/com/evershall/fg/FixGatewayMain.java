package com.evershall.fg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.ConfigError;

import java.io.IOException;

/**
 * Created by ngehring on 3/31/17.
 */
public class FixGatewayMain {

    private static final Logger LOG = LoggerFactory.getLogger(FixGatewayMain.class);


    public static void main(String[] args) throws ConfigError {
        LOG.info("Starting FixGateway");

        try {
            new FixGatewayContainer().start();
        } catch (IOException e) {
            LOG.error("Cannot start FixGateway", e);
        }

        LOG.info("FixGateway running");
    }

}
