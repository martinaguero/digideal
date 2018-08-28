package org.trimatek.digideal.actions;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.logging.Level;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.trimatek.digideal.Config;
import org.trimatek.digideal.bitcoin.tools.Calc;
import org.trimatek.digideal.comm.mail.SendMessage;
import org.trimatek.digideal.comm.mail.Template;
import org.trimatek.digideal.comm.mail.Tools;
import org.trimatek.digideal.comm.mail.utils.TemplateFactory;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.tools.Dialogs;
import org.trimatek.digideal.tools.Generators;

public class ReRequestFunds extends Action {

	@Override
	public Contract exec(Contract cnt) throws Exception {

		logger.log(Level.INFO, "Ready to send mail requesting more funds");
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
		String locale = cnt.getSource().getLocale();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		BigDecimal dif = calcDifference(cnt);

		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("payer.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(Dialogs.read("email_subject_prefix",locale) + " " + Dialogs.read("email_rerequest_funds_subject",locale));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(Dialogs.read("email_hi",locale) + " @" + cnt.getValue("payer.name") + "</b>");
		String content1 = Dialogs.read("email_btc",locale) + " "
				+ cnt.getLastUnspentTransaction().getValue() + " " + Dialogs.read("email_rerequest_funds_content1_a",locale)
				+ "<br/> " + Dialogs.read("email_rerequest_funds_content1_b",locale) + " <b>"
				+ Dialogs.read("email_btc",locale) + " " + dif + "</b> "
				+ Dialogs.read("email_rerequest_funds_content1_c",locale) + "<br/>" + cnt.getMultisigAddress() + "<br/>"
				+ Dialogs.read("email_rerequest_funds_content1_d",locale);

		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2(Dialogs.read("email_content2",locale) + cnt.getValue("id"));
		t.setSalutation(Dialogs.read("email_salutation",locale));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");
		content.addBodyPart(htmlPart);

		byte[] qr = Generators.genQRCodeImage(Generators.genQRSendTo(cnt, dif), Config.TAMANIO_QR, Config.TAMANIO_QR);
		content.addBodyPart(Tools.addImage(qr, cnt.getValue("id") + ".png"));
		
		email.setContent(content);
		return email;
	}

	private static BigDecimal calcDifference(Contract cnt) throws IOException {
		return new BigDecimal(cnt.getValue("btc")).subtract(Calc.addTransactions(cnt.getUnspentTransactions()));
	}

}
