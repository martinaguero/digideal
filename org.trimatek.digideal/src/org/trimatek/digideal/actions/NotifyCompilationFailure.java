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
import org.trimatek.digideal.comm.mail.Tools;
import org.trimatek.digideal.comm.mail.utils.TemplateFactory;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.utils.Config;
import org.trimatek.digideal.tools.Dialogs;

public class NotifyCompilationFailure extends Action {

	@Override
	public Contract exec(Contract contract) throws Exception {

		logger.log(Level.INFO, "Ready to notify contract compilation failure");
		SendMessage.exec(setupAdminMail(contract));
		logger.log(Level.INFO, "Message sent successfully");
		done = Boolean.TRUE;

		return contract;
	}

	private static MimeMessage setupAdminMail(Contract cnt) throws Exception {
		Properties props = new Properties();
		String locale = cnt.getSource().getLocale();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress(Config.getValue("ADMIN_MAIL")));
		InternetAddress[] to = InternetAddress.parse(Config.getValue("ADMIN_MAIL"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(Dialogs.read("email_subject_prefix",locale) + " Compilation failure");

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi("");
		t.setPreview(cnt.getComments());
		t.setContent1(cnt.getComments());
		t.setContent2(Dialogs.read("email_content2",locale) + cnt.getSource().getName());
		t.setSalutation("");
		t.setSupport(Dialogs.read("email_need_support", locale), Dialogs.read("email_contact_us", locale),
				Dialogs.getSupportUrl("null","null"));
		t.setVersion(Config.getValue("DIGIDEAL_VERSION"));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);
		content.addBodyPart(Tools.addPdf(cnt.getSource().getPdf(),
				Dialogs.read("contract_header", locale) + cnt.getSource().getName() + ".pdf"));
		
		email.setContent(content);
		return email;
	}
	
}
