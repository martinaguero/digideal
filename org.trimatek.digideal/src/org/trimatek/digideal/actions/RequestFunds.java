package org.trimatek.digideal.actions;

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
import org.trimatek.digideal.tools.Dialogs;
import org.trimatek.digideal.tools.Generators;

public class RequestFunds extends Action {

	@Override
	public Contract exec(Contract cnt) throws Exception {

		logger.log(Level.INFO, "Ready to send mail requesting contract funds");
		MimeMessage payerMail = setupPayerMail(cnt);
		MimeMessage collectorMail = setupCollectorMail(cnt);
		Object result = SendMessage.exec(payerMail);
		SendMessage.exec(collectorMail);
		if (result != null && !result.equals("")) {
			logger.log(Level.INFO, "Message send successfully");
			done = Boolean.TRUE;
			return cnt;
		}
		logger.log(Level.SEVERE, "Message could not be send");
		return null;
	}

	private static MimeMessage setupPayerMail(Contract cnt) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("payer.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject("[DD] Funds request for contract ID: " + cnt.getValue("id"));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();
		String qrString = Generators.genQRSendTo(cnt,null);
		byte[] qr = Generators.genQRCodeImage(qrString, Config.TAMANIO_QR, Config.TAMANIO_QR);
		
		htmlPart.setText(
				"<html><body><p> The attached contract has been created.<br/>" + "Please send BTC "
						+ cnt.getValue("btc") + " to address: <br/>" + cnt.getMultisigAddress() + "<br/>"
						+ "<a href=\"url\">" + qrString + "</a><br/>"
						+ "in order to proceed with contract: " + cnt.getValue("id") + " requirements. <br/><br/>"
						+ "Then, please reply this message with the transaction ID." + "</p><br/>"
						+ "<div style=\"display:none;\"> " + cnt.getValue("id") + " </div></body></html>",
				"US-ASCII", "html");
		
		content.addBodyPart(htmlPart);
		content.addBodyPart(Tools.addImage(qr, cnt.getValue("id") + ".png"));
		content.addBodyPart(Tools.addPdf(cnt.getSource().getPdf(),
				Dialogs.msg.getString("contract_header") + cnt.getValue("id") + ".pdf"));
		email.setContent(content);

		return email;
	}

	private static MimeMessage setupCollectorMail(Contract cnt) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("collector.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject("[DD] New contract with ID: " + cnt.getValue("id"));
		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		htmlPart.setText("<html><body><p>" + "The attached contract has been created.<br/>"
				+ "Please wait to our notification in order to deliver the requested product or service.</p><br/>"
				+ "<div style=\"display:none;\"> " + cnt.getValue("id") + " </div></body></html>", "US-ASCII", "html");
		content.addBodyPart(htmlPart);

		content.addBodyPart(Tools.addPdf(cnt.getSource().getPdf(),
				Dialogs.msg.getString("contract_header") + cnt.getValue("id") + ".pdf"));
		email.setContent(content);
		return email;
	}



}
