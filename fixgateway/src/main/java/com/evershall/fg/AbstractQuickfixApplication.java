package com.evershall.fg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.*;

/**
 * Created by ngehring on 3/31/17.
 */
public class AbstractQuickfixApplication extends MessageCracker implements Application {

   private static final Logger LOG = LoggerFactory.getLogger(AbstractQuickfixApplication.class);

   public void onCreate (SessionID sessionId) {
      LOG.info("onCreate : sessionId = {}", sessionId);
   }

   public void onLogon (SessionID sessionId) {
      LOG.info("onLogon : sessionId = {}", sessionId);
   }

   public void onLogout (SessionID sessionId) {
      LOG.info("onLogout : sessionId = {}", sessionId);
   }

   public void toAdmin (Message message, SessionID sessionId) {
      LOG.info("toAdmin : message = {}, sessionId = {}", message, sessionId);
   }

   public void fromAdmin (Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
      LOG.info("fromAdmin : message = {}, sessionId = {}", message, sessionId);
   }

   public void toApp (Message message, SessionID sessionId) throws DoNotSend {
      LOG.info("toApp : message = {}, sessionId = {}", message, sessionId);
   }

   public void fromApp (Message message, SessionID sessionId) throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
      LOG.info("fromApp : message = {}, sessionId = {}", message, sessionId);
   }

}
