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

import org.trimatek.digideal.comm.mail.SendMessage;
import org.trimatek.digideal.comm.mail.Template;
import org.trimatek.digideal.comm.mail.utils.TemplateFactory;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;
import org.trimatek.digideal.model.utils.Config;
import org.trimatek.digideal.tools.Dialogs;

public class NotifyFundsAvailability extends Action {

	@Override
	public Contract exec(Contract contract) throws Exception {

		logger.log(Level.INFO, "Ready to notify funds availability");
		SendMessage.exec(setupCollectorMail(contract));
		logger.log(Level.INFO, "Message sent successfully");
		done = Boolean.TRUE;

		return contract;
	}

	private static MimeMessage setupCollectorMail(Contract cnt) throws Exception {
		Properties props = new Properties();
		String locale = cnt.getSource().getLocale();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("collector.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(Dialogs.read("email_subject_prefix", locale) + " "
				+ Dialogs.read("email_funds_available_subject", locale));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(Dialogs.read("email_hi", locale) + " @" + cnt.getValue("collector.name") + "</b>");
		String content1 = Dialogs.read("email_funds_available_collector_content1_a", locale) + "<br/>"
				+ Dialogs.getUnspentTxUrls(cnt) + "<br/>"
				+ Dialogs.read("email_funds_available_collector_content1_b", locale) + " "
				+ setTwitterColor(" @" + cnt.getValue("payer.name") + " ")
				+ Dialogs.read("email_funds_available_collector_content1_c", locale);
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2(Dialogs.read("email_content2", locale) + cnt.getValue("id"));
		t.setSalutation(Dialogs.read("email_salutation", locale));
		t.setSupport(Dialogs.read("email_need_support", locale), Dialogs.read("email_contact_us", locale),
				Dialogs.getSupportUrl(cnt.getSource().getName(), cnt.getValue("collector.email")));
		t.setVersion(Config.getValue("DIGIDEAL_VERSION"));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);
		email.setContent(content);
		return email;
	}

}
