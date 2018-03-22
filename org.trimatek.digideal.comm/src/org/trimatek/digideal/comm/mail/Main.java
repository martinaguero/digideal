package org.trimatek.digideal.comm.mail;

import java.util.List;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;



public class Main {

	public static void main(String[] args) throws Exception {

		List<Message> messages = (List<Message>) LoadMessages.exec("is:unread to:(aguero.martin@gmail.com)");
		
		System.out.println(messages.size());
		for (Message m : messages) {
			Message msg = (Message)GetMessage.exec(m.getId());
			System.out.println(msg.toPrettyString());
		}

//		Properties props = new Properties();
//		Session session = Session.getDefaultInstance(props, null);
//		MimeMessage email = new MimeMessage(session);
//
//		email.setFrom(new InternetAddress("aguero.martin@gmail.com", "DigiDeal"));
//		InternetAddress[] to = InternetAddress.parse("aguero.martin@gmail.com");
//		email.setRecipients(RecipientType.TO, to);
//		email.setSubject("[DD] Funds request");
//		email.setText("<html><body><p>Please send STS </p></body></html>");
//
//		SendMessage.exec(email);

	}

}
