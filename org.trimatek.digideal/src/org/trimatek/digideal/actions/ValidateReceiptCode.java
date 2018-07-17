package org.trimatek.digideal.actions;

import java.util.Properties;
import java.util.logging.Level;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.trimatek.digideal.Config;
import org.trimatek.digideal.agent.Agent;
import org.trimatek.digideal.comm.mail.SendMessage;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Receipt;

public class ValidateReceiptCode extends Action {

	@Override
	public Contract exec(Contract cnt) throws Exception {
		logger.log(Level.INFO, "Ready to validate receipt code");
		Receipt result = Agent.validateReceiptCode(cnt, cnt.getReceipt());
		if (result != null && result.isValid()) {
			cnt.setReceipt(result);
			logger.log(Level.INFO, "Receipt code valid");
			done = Boolean.TRUE;
			return cnt;
		} else {
			SendMessage.exec(setupCollectorMail(cnt));
			logger.log(Level.INFO, "Receipt code not valid");
		}		
		return null;		
	}
	
	private static MimeMessage setupCollectorMail(Contract cnt) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("collector.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(Config.MAIL_SUBJECT_HEADER + " Receipt code not valid");
		email.setText("Receipt code: " + cnt.getReceipt().getCode() + " not valid. Please try again.");
		return email;	
	}

public static void main(String[] args) throws Exception {
		
		Contract c = new Contract();
		c.setReceiptCode("123.123");
		Receipt r = new Receipt("123122");
		c.setReceipt(r);
		
		ValidateReceiptCode val = new ValidateReceiptCode();
		c = val.exec(c);
		
		System.out.println("Remito validado: " + c.getReceipt().isValid());

	
	}

}
