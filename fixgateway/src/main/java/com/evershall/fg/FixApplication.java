package com.evershall.fg;

import quickfix.*;

/**
 * Created by ngehring on 3/31/17.
 */
public class FixApplication extends AbstractQuickfixApplication {

   private final SocketAcceptor acceptor;

   public FixApplication () throws ConfigError {
      SessionSettings settings = new SessionSettings("server.quickfix.properties");
      FileStoreFactory fileStoreFactory = new FileStoreFactory(settings);
      ScreenLogFactory screenLogFactory = new ScreenLogFactory(settings);
      DefaultMessageFactory msgFactory = new DefaultMessageFactory();
      acceptor = new SocketAcceptor(this, fileStoreFactory, settings, screenLogFactory, msgFactory);
   }

   public void start () throws ConfigError {

      acceptor.start();


   }
}
