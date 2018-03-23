package org.trimatek.digideal.comm.mail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

public class Tools {

	/**
	 * Get a Message and use it to create a MimeMessage.
	 *
	 * @param service
	 *            Authorized Gmail API instance.
	 * @param userId
	 *            User's email address. The special value "me" can be used to
	 *            indicate the authenticated user.
	 * @param messageId
	 *            ID of Message to retrieve.
	 * @return MimeMessage MimeMessage populated from retrieved Message.
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static MimeMessage getMimeMessage(Gmail service, String userId, String messageId)
			throws IOException, MessagingException {
		Message message = service.users().messages().get(userId, messageId).setFormat("raw").execute();

		Base64 base64Url = new Base64(true);
		byte[] emailBytes = base64Url.decodeBase64(message.getRaw());

		Session session = Session.getDefaultInstance(new Properties(), null);

		MimeMessage email = new MimeMessage(session, new ByteArrayInputStream(emailBytes));
		email.setHeader("Gmail-ID", messageId);

		return email;
	}

	public static BodyPart addImage(byte[] image, String fileName) throws MessagingException {
		InternetHeaders headers = new InternetHeaders();
		headers.addHeader("Content-Type", "image/jpeg");
		headers.addHeader("Content-Transfer-Encoding", "base64");
		MimeBodyPart imagePart = new MimeBodyPart(headers, image);
		imagePart.setDisposition(MimeBodyPart.INLINE);
		imagePart.setContentID("&lt;image&gt;");
		imagePart.setFileName(fileName);
		return imagePart;
	}

}
