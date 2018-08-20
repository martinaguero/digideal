package org.trimatek.digideal.actions;

import static org.trimatek.digideal.bitcoin.entities.Context.MAINNET;
import static org.trimatek.digideal.bitcoin.entities.Context.TX_TRACK_MAINNET;
import static org.trimatek.digideal.bitcoin.entities.Context.TX_TRACK_TESTNET;
import static org.trimatek.digideal.tools.Dialogs.msg;
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
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("payer.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(msg.getString("email_subject_prefix") + " " + msg.getString("email_contract_success_subject"));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(msg.getString("email_hi") + " @" + cnt.getValue("payer.name") + "</b>");
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2(msg.getString("email_content2") + cnt.getValue("id"));
		t.setSalutation(msg.getString("email_salutation"));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);
		email.setContent(content);
		return email;
	}

	private static MimeMessage setupCollectorMail(Contract cnt, String content1) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("collector.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(msg.getString("email_subject_prefix") + " " + msg.getString("email_contract_success_subject"));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(msg.getString("email_hi") + " @" + cnt.getValue("collector.name") + "</b>");
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2(msg.getString("email_content2") + cnt.getValue("id"));
		t.setSalutation(msg.getString("email_salutation"));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);
		email.setContent(content);
		return email;
	}

	private String setupContent(Contract cnt) throws IOException {
		String txUrl = MAINNET
				? setHiperlink(msg.getString("email_contract_success_content1_c"), 16,
						TX_TRACK_MAINNET + cnt.getSpentTxId())
				: setHiperlink(msg.getString("email_contract_success_content1_c"), 16,
						TX_TRACK_TESTNET + cnt.getSpentTxId());
		return msg.getString("email_contract_success_content1_a")
				+ setTwitterColor(" @" + cnt.getValue("collector.name") + " ")
				+ msg.getString("email_contract_success_content1_b") + " " + txUrl + ".<br/>"
				+ msg.getString("email_contract_success_content1_d");
	}

}
