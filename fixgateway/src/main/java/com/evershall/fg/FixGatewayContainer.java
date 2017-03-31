package com.evershall.fg;

import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import net.openhft.chronicle.ExcerptAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.ConfigError;

import java.io.IOException;

/**
 * Created by ngehring on 3/31/17.
 */
public class FixGatewayContainer {

   private static final Logger LOG = LoggerFactory.getLogger(FixGatewayContainer.class);
   private final ExcerptAppender appender;

   public FixGatewayContainer () throws IOException {
      LOG.trace("constructing");

      Chronicle chronicle = ChronicleQueueBuilder.vanilla("/tmp/evershall/jnl/").build();
      appender = chronicle.createAppender();

      LOG.trace("constructed");
   }


   public void start () throws ConfigError {
      LOG.trace("starting");

      FixApplication fixApplication = new FixApplication();
      fixApplication.start();

      LOG.trace("started");
   }

}
