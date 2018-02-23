package org.trimatek.digideal.comm.mail;

import java.io.ByteArrayOutputStream;
import java.util.logging.Level;

import javax.mail.internet.MimeMessage;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

public class SendMessage extends Resource {

	/**
	 * 
	 * @param params
	 *            MimeMessage
	 * @throws Exception
	 */

	public static String exec(Object... params) throws Exception {
		Gmail service = Setup.getGmailService();

		Message message = createMessageWithEmail((MimeMessage) params[0]);
		message = service.users().messages().send(Setup.USER, message).execute();

//		System.out.println("Message id: " + message.getId());
//		System.out.println(message.toPrettyString());
		logger.log(Level.INFO, "Sent message with id: " + message.getId());
		
		return message.getId();

	}

	public static Message createMessageWithEmail(MimeMessage email) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		email.writeTo(baos);
		String encodedEmail = Base64.encodeBase64URLSafeString(baos.toByteArray());
		Message message = new Message();
		message.setRaw(encodedEmail);
		return message;
	}

}
