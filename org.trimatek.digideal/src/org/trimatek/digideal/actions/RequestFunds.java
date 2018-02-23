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
		/*
		Email from = new Email(cnt.getValue("agent.email"));
		String subject = "[DD] Funds request";
		Email to = new Email(cnt.getValue("payer.email"));

		String html = "<html><body><p>Please send STS " + cnt.getValue("sts") + " to address: <br><b>"
				+ cnt.getMultisigAddress() + "</b><br> in order to proceed with the contract requirements."
				+ "</p></body></html>";

		Content content = new Content("text/html", html);

		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();

		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());
		*/

		logger.log(Level.INFO, "Ready to send mail requesting contract funds");
		String result = SendMessage.exec(setupMail(cnt));
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
		email.setText("<html><body><p>Please send STS " + cnt.getValue("sts") + " to address: <br><b>"
				+ cnt.getMultisigAddress() + "</b><br> in order to proceed with the contract requirements."
				+ "</p></body></html>");
		return email;	
	}

}
