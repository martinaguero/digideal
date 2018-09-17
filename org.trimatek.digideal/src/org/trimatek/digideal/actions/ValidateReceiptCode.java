package org.trimatek.digideal.actions;

import java.util.Properties;
import java.util.logging.Level;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.trimatek.digideal.agent.Agent;
import org.trimatek.digideal.comm.mail.SendMessage;
import org.trimatek.digideal.comm.mail.Template;
import org.trimatek.digideal.comm.mail.utils.TemplateFactory;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Receipt;
import org.trimatek.digideal.tools.Dialogs;

public class ValidateReceiptCode extends Action {

	@Override
	public Contract exec(Contract cnt) throws Exception {
		logger.log(Level.INFO, "Ready to validate receipt code");
		Receipt result = Agent.validateReceiptCode(cnt, cnt.getReceipt());
		if (result != null && result.isValid()) {
			cnt.setReceipt(result);
			logger.log(Level.INFO, "Receipt code valid");
			done = Boolean.TRUE;
			return cnt;
		} else {
			SendMessage.exec(setupCollectorMail(cnt));
			logger.log(Level.INFO, "Receipt code not valid");
		}
		return null;
	}

	private static MimeMessage setupCollectorMail(Contract cnt) throws Exception {
		Properties props = new Properties();
		String locale = cnt.getSource().getLocale();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("collector.email"));
		email.setRecipients(RecipientType.TO, to);

		email.setSubject(
				Dialogs.read("email_subject_prefix",locale) + " " + Dialogs.read("email_validate_receipt_code_subject",locale));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(Dialogs.read("email_hi",locale) + " @" + cnt.getValue("collector.name") + "</b>");
		String content1 = Dialogs.read("email_validate_receipt_code_content1_a",locale) + " <b>" + cnt.getReceipt().getCode() + "</b><br/>"
				+ Dialogs.read("email_validate_receipt_code_content1_b",locale);
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2(Dialogs.read("email_content2",locale) + cnt.getValue("id"));
		t.setSalutation(Dialogs.read("email_salutation",locale));
		t.setSupport(Dialogs.read("email_need_support", locale), Dialogs.read("email_contact_us", locale),
				Dialogs.getSupportUrl(cnt.getSource().getName(),cnt.getValue("collector.email")));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);
		email.setContent(content);
		return email;
	}

	public static void main(String[] args) throws Exception {

		Contract c = new Contract();
		c.setReceiptCode("123.123");
		Receipt r = new Receipt("123122");
		c.setReceipt(r);

		ValidateReceiptCode val = new ValidateReceiptCode();
		c = val.exec(c);

		System.out.println("Remito validado: " + c.getReceipt().isValid());

	}

}
