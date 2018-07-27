package org.trimatek.digideal.actions;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.trimatek.digideal.Config;
import org.trimatek.digideal.comm.mail.SendMessage;
import org.trimatek.digideal.comm.mail.Tools;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.tools.Generators;

import com.google.zxing.WriterException;

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

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();
		byte[] qr = Generators.genQRCodeImage(genQRSendTo(cnt), Config.TAMANIO_QR, Config.TAMANIO_QR);
		htmlPart.setText(
				"<html><body>" + "<div style=\"display:none;\"> " + cnt.getValue("id") + " </div>" + "<p>"
						+ "Please send BTC " + cnt.getValue("btc") + " to address: \n" + cnt.getMultisigAddress() + "\n"
						+ "in order to proceed with contract SN: " + cnt.getValue("id") + " requirements. \n\n"
						+ "Then, please reply this message with the transaction ID." + "</p></body></html>",
				"US-ASCII", "html");
		content.addBodyPart(htmlPart);
		content.addBodyPart(Tools.addImage(qr, cnt.getValue("id") + ".png"));

		return email;
	}

	private static String genQRSendTo(Contract cnt) throws IOException {
		return "bitcoin:" + cnt.getValue("collector.address") + "?amount=" + cnt.getValue("btc");
	}

	public static void main(String args[]) throws FileNotFoundException, IOException, WriterException {

		Contract cnt = new Contract(null,
				"C:\\Users\\aguer\\Dropbox\\Criptomonedas\\digideal\\contrato\\QUINTO.properties");
		String text = genQRSendTo(cnt);
		byte[] qr = Generators.genQRCodeImage(text, Config.TAMANIO_QR, Config.TAMANIO_QR);

		FileOutputStream stream = new FileOutputStream("c:\\Temp\\qr.png");
		try {
			stream.write(qr);
		} finally {
			stream.close();
		}

	}

}
