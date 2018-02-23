package org.trimatek.digideal.comm.mail;

import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {

	public static void main(String[] args) throws Exception {

		// LoadMessagesWithLabelId.exec("Label_14");

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress("aguero.martin@gmail.com", "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse("aguero.martin@gmail.com");
		email.setRecipients(RecipientType.TO, to);
		email.setSubject("[DD] Funds request");
		email.setText("<html><body><p>Please send STS </p></body></html>");

		SendMessage.exec(email);

	}

}
