package org.trimatek.digideal.actions;

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
import static org.trimatek.digideal.tools.Dialogs.msg;
import static org.trimatek.digideal.tools.Dialogs.setTwitterColor;

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
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("collector.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(msg.getString("email_subject_prefix") + " " + msg.getString("email_funds_available_subject"));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(msg.getString("email_hi") + " @" + cnt.getValue("collector.name") + "</b>");
		String content1 = msg.getString("email_funds_available_collector_content1_a") + "<br/>"
				+ msg.getString("email_funds_available_collector_content1_b") + " "
				+ setTwitterColor(" @" + cnt.getValue("payer.name") + " ")
				+ msg.getString("email_funds_available_collector_content1_c");
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2(msg.getString("email_content2") + cnt.getValue("id"));
		t.setSalutation(msg.getString("email_salutation"));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);
		email.setContent(content);
		return email;
	}

}
