package org.trimatek.digideal.actions;

import static org.trimatek.digideal.bitcoin.entities.Context.MAINNET;
import static org.trimatek.digideal.bitcoin.entities.Context.TX_TRACK_MAINNET;
import static org.trimatek.digideal.bitcoin.entities.Context.TX_TRACK_TESTNET;
import static org.trimatek.digideal.tools.Dialogs.setHiperlink;
import static org.trimatek.digideal.tools.Dialogs.setTwitterColor;

import java.io.IOException;
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
import org.trimatek.digideal.tools.Dialogs;

public class NotifyContractSuccess extends Action {

	@Override
	public Contract exec(Contract contract) throws Exception {

		logger.log(Level.INFO, "Ready to notify contract success");
		String content1 = setupContent(contract);
		SendMessage.exec(setupPayerMail(contract, content1));
		SendMessage.exec(setupCollectorMail(contract, content1));
		logger.log(Level.INFO, "Messages sent successfully");
		done = Boolean.TRUE;

		return contract;
	}

	private static MimeMessage setupPayerMail(Contract cnt, String content1) throws Exception {
		Properties props = new Properties();
		String locale = cnt.getSource().getLocale();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("payer.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(Dialogs.read("email_subject_prefix",locale) + " " + Dialogs.read("email_contract_success_subject",locale));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(Dialogs.read("email_hi",locale) + " @" + cnt.getValue("payer.name") + "</b>");
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2(Dialogs.read("email_content2",locale) + cnt.getValue("id"));
		t.setSalutation(Dialogs.read("email_salutation",locale));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);
		email.setContent(content);
		return email;
	}

	private static MimeMessage setupCollectorMail(Contract cnt, String content1) throws Exception {
		Properties props = new Properties();
		String locale = cnt.getSource().getLocale();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("collector.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(Dialogs.read("email_subject_prefix",locale) + " " + Dialogs.read("email_contract_success_subject",locale));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(Dialogs.read("email_hi",locale) + " @" + cnt.getValue("collector.name") + "</b>");
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2(Dialogs.read("email_content2",locale) + cnt.getValue("id"));
		t.setSalutation(Dialogs.read("email_salutation",locale));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);
		email.setContent(content);
		return email;
	}

	private String setupContent(Contract cnt) throws IOException {
		String locale = cnt.getSource().getLocale();
		String txUrl = MAINNET
				? setHiperlink(Dialogs.read("email_contract_success_content1_c",locale), 16,
						TX_TRACK_MAINNET + cnt.getSpentTxId())
				: setHiperlink(Dialogs.read("email_contract_success_content1_c",locale), 16,
						TX_TRACK_TESTNET + cnt.getSpentTxId());
		return Dialogs.read("email_contract_success_content1_a",locale)
				+ setTwitterColor(" @" + cnt.getValue("collector.name") + " ")
				+ Dialogs.read("email_contract_success_content1_b",locale) + " " + txUrl + ".<br/>"
				+ Dialogs.read("email_contract_success_content1_d",locale);
	}

}
