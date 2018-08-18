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
import org.trimatek.digideal.comm.mail.Template;
import org.trimatek.digideal.comm.mail.Tools;
import org.trimatek.digideal.comm.mail.utils.TemplateFactory;
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
		email.setSubject(Dialogs.msg.getString("email_subject_prefix") + " "
				+ Dialogs.msg.getString("email_request_funds_subject"));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();
		String qrString = Generators.genQRSendTo(cnt, null);
		byte[] qr = Generators.genQRCodeImage(qrString, Config.TAMANIO_QR, Config.TAMANIO_QR);

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(Dialogs.msg.getString("email_hi") + " @" + cnt.getValue("payer.name") + "</b>");
		String content1 = Dialogs.msg.getString("email_request_funds_payer_content1_a") + " " + cnt.getValue("btc")
				+ " " + Dialogs.msg.getString("email_request_funds_payer_content1_b") + " " + cnt.getMultisigAddress()
				+ "<br/> " + Dialogs.msg.getString("email_request_funds_payer_content1_c") + " @"
				+ cnt.getValue("collector.name") + ".";
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2("");
		t.setSalutation(Dialogs.msg.getString("email_salutation"));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

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
		email.setSubject(Dialogs.msg.getString("email_subject_prefix") + " "
				+ Dialogs.msg.getString("email_request_funds_subject")); 
		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(Dialogs.msg.getString("email_hi") + " @" + cnt.getValue("collector.name") + "</b>");
		String content1 = Dialogs.msg.getString("email_request_funds_collector_content1");
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2("");
		t.setSalutation(Dialogs.msg.getString("email_salutation"));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);

		content.addBodyPart(Tools.addPdf(cnt.getSource().getPdf(),
				Dialogs.msg.getString("contract_header") + cnt.getValue("id") + ".pdf"));
		email.setContent(content);
		return email;
	}

}
