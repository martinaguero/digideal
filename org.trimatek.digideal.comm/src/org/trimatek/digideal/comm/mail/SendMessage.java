package org.trimatek.digideal.comm.mail;

import java.io.ByteArrayOutputStream;
import java.util.logging.Level;

import javax.mail.internet.MimeMessage;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.model.Message;

public class SendMessage extends GMailResource {

	/**
	 * @param params
	 *            MimeMessage
	 * @return String
	 * @throws Exception
	 */

	public static Object exec(Object... params) throws Exception {
		Message message = createMessageWithEmail((MimeMessage) params[0]);
		message = Setup.getGmailService().users().messages().send(Setup.USER, message).execute();
		logger.log(Level.INFO, "Sent message with id: " + message.getId() + " history id: " + message.getHistoryId()
				+ " thread id: " + message.getThreadId());
		return message;
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
