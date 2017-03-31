package com.evershall.fg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;
import quickfix.fix44.Logout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by ngehring on 3/31/17.
 */
public class DummyClient extends AbstractQuickfixApplication {

   private static final Logger LOG = LoggerFactory.getLogger(DummyClient.class);

   private SocketInitiator socketInitiator;


   public static void main (final String[] args) throws ConfigError, InterruptedException, IOException, SessionNotFound, InvalidMessage {
      new DummyClient().startInitiator(DummyClient.class.getClassLoader().getResourceAsStream("dummyClient.properties"));
   }

   private void startInitiator (final InputStream propsStream) throws ConfigError, InterruptedException, IOException, SessionNotFound, InvalidMessage {

      final SessionSettings sessionSettings = new SessionSettings(propsStream);
      final FileStoreFactory fileStoreFactory = new FileStoreFactory(sessionSettings);
      final LogFactory logFactory = new FileLogFactory(sessionSettings);
      final MessageFactory messageFactory = new DefaultMessageFactory();
      socketInitiator = new SocketInitiator(this, fileStoreFactory, sessionSettings, logFactory, messageFactory);
      socketInitiator.start();

      processCommands();

      final ArrayList<SessionID> sessions = socketInitiator.getSessions();
      final SessionID sessionID = sessions.get(0);
      sendLogout(sessionID);

      socketInitiator.stop();
   }

   private void processCommands () {

      try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

         String line;
         while ((line = br.readLine()) != null) {

            if ("bye".equals(line))
               break;

            else
               System.out.println("Unknown command");

         }

      } catch (final Exception e) {
         e.printStackTrace();
      }
   }

   private static void sendLogout (final SessionID sessionID) throws SessionNotFound {
      final Logout logout = new Logout();
      Session.sendToTarget(logout, sessionID);
   }

   @Override
   public void onLogon (SessionID sessionId) {
      super.onLogon(sessionId);
      LOG.info("enter 'bye' to quit gracefully");
   }
}
