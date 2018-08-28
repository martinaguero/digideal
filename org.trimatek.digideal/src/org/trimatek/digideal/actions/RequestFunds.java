package org.trimatek.digideal.actions;

import static org.trimatek.digideal.tools.Dialogs.setTwitterColor;

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
		String locale = cnt.getSource().getLocale();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("payer.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(Dialogs.read("email_subject_prefix",locale) + " " + Dialogs.read("email_request_funds_subject",locale));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();
		String qrString = Generators.genQRSendTo(cnt, null);
		byte[] qr = Generators.genQRCodeImage(qrString, Config.TAMANIO_QR, Config.TAMANIO_QR);

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(Dialogs.read("email_hi",locale) + " @" + cnt.getValue("payer.name") + "</b>");
		String content1 = Dialogs.read("email_request_funds_payer_content1_a",locale) + " <b>" + Dialogs.read("email_btc",locale)
				+ " " + cnt.getValue("btc") + "</b> " + Dialogs.read("email_request_funds_payer_content1_b",locale) + " "
				+ cnt.getMultisigAddress() + "<br/> " + Dialogs.read("email_request_funds_payer_content1_c",locale)
				+ setTwitterColor(" @" + cnt.getValue("collector.name")) + ".";
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2(Dialogs.read("email_content2",locale) + cnt.getValue("id"));
		t.setSalutation(Dialogs.read("email_salutation",locale));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);

		content.addBodyPart(Tools.addImage(qr, cnt.getValue("id") + ".png"));
		content.addBodyPart(
				Tools.addPdf(cnt.getSource().getPdf(), Dialogs.read("contract_header",locale) + cnt.getValue("id") + ".pdf"));
		email.setContent(content);

		return email;
	}

	private static MimeMessage setupCollectorMail(Contract cnt) throws Exception {
		Properties props = new Properties();
		String locale = cnt.getSource().getLocale();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("collector.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(Dialogs.read("email_subject_prefix",locale) + " " + Dialogs.read("email_request_funds_subject",locale));
		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(Dialogs.read("email_hi",locale) + " @" + cnt.getValue("collector.name") + "</b>");
		String content1 = Dialogs.read("email_request_funds_collector_content1",locale)
				+ setTwitterColor(" @" + cnt.getValue("payer.name")) + ".";
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2(Dialogs.read("email_content2",locale) + cnt.getValue("id"));
		t.setSalutation(Dialogs.read("email_salutation",locale));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);

		content.addBodyPart(
				Tools.addPdf(cnt.getSource().getPdf(), Dialogs.read("contract_header",locale) + cnt.getValue("id") + ".pdf"));
		email.setContent(content);
		return email;
	}

}
