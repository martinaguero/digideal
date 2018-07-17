package org.trimatek.digideal.actions;

import java.util.Properties;
import java.util.logging.Level;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.trimatek.digideal.comm.mail.SendMessage;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;

public class RequestFunds extends Action {

	@Override
	public Contract exec(Contract cnt) throws Exception {

		logger.log(Level.INFO, "Ready to send mail requesting contract funds");
		Object result = SendMessage.exec(setupMail(cnt));
		if (result != null && !result.equals("")) {
			logger.log(Level.INFO, "Message send successfully");
			done = Boolean.TRUE;
			return cnt;
		}
		logger.log(Level.SEVERE, "Message could not be send");
		return null;
	}

	private static MimeMessage setupMail(Contract cnt) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("payer.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject("[DD] Funds request");
		email.setText("Please send BTC " + cnt.getValue("btc") + " to address: \n"
				+ cnt.getMultisigAddress() + "\n"
				+ "in order to proceed with contract SN: " + cnt.getValue("id") + " requirements. \n\n"
				+ "Then, please reply this message with the transaction ID.");
		return email;
	}

}
