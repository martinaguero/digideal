package org.trimatek.digideal.tools;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.sun.mail.util.BASE64DecoderStream;

public class Mail {

	protected final static Logger logger = Logger.getLogger(Mail.class.getName());

	public static int sendMail(String s, String a, String b, String[] c) {
		return 0;
	}

	public static String getMessageTextContent(Message message) throws MessagingException, IOException {
		Object content = message.getContent();
		if (content instanceof Multipart) {
			StringBuffer messageContent = new StringBuffer();
			Multipart multipart = (Multipart) content;
			for (int i = 0; i < multipart.getCount(); i++) {
				Part part = multipart.getBodyPart(i);
				if (part.getContentType().contains("text/")) {
					messageContent.append(part.getContent().toString());
				}
			}
			return messageContent.toString();
		}
		return content.toString();
	}

	public static Map<String, Object> getAttachments(Message message)
			throws MessagingException, IOException, NotFoundException, ChecksumException, FormatException {
		Map<String, Object> attMap = new HashMap<String, Object>();
		Object content = message.getContent();
		if (content instanceof Multipart) {
			Multipart multiPart = (Multipart) content;
			int numberOfParts = multiPart.getCount();
			for (int i = 0; i < numberOfParts; i++) {
				MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);			
				if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())
						|| part.getContentType().contains("image/")) {
					attMap.put(part.getFileName(), part.getContent());
				}
			}
		}
		return attMap;
	}

}
