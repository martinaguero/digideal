package org.trimatek.digideal.actions;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.regex.Pattern;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.trimatek.digideal.Context;
import org.trimatek.digideal.comm.mail.SendMessage;
import org.trimatek.digideal.comm.mail.Template;
import org.trimatek.digideal.comm.mail.Tools;
import org.trimatek.digideal.comm.mail.utils.TemplateFactory;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.utils.Config;
import org.trimatek.digideal.tools.Dialogs;
import org.trimatek.digideal.tools.Generators;

public class SendReceiptCode extends Action {

	@Override
	public Contract exec(Contract cnt) throws Exception {

		logger.log(Level.INFO, "Ready to send receipt code");
		cnt.setReceiptCode(Generators.genNewDeliveryCode());
		byte[] qr = Generators.genQRCodeImage(genQRGmail(cnt), Context.TAMANIO_QR, Context.TAMANIO_QR);
		Object result = SendMessage.exec(setupMail(cnt, qr, cnt.getReceiptCode()));

		if (result != null && !result.equals("")) {
			logger.log(Level.INFO, "Message send successfully");
			done = Boolean.TRUE;
			return cnt;
		}

		logger.log(Level.SEVERE, "Receive code could not be send");
		return null;
	}

	private static String getTx(String content) {
		content = content.replace(" ", System.lineSeparator());
		String[] words = content.split(System.lineSeparator());
		for (String w : words) {
			if (Pattern.matches(Context.TX_REGEX, w)) {
				return w;
			}
		}
		return null;
	}

	private static MimeMessage setupMail(Contract cnt, byte[] qr, String code) throws Exception {
		Properties props = new Properties();
		String locale = cnt.getSource().getLocale();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("payer.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject(Dialogs.read("email_subject_prefix", locale) + " "
				+ Dialogs.read("email_funds_available_subject", locale));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();

		Template t = TemplateFactory.getEmailTemplate();
		t.setHi(Dialogs.read("email_hi", locale) + " @" + cnt.getValue("payer.name") + "</b>");
		String content1 = Dialogs.read("email_funds_available_collector_content1_a", locale) + "<br/>"
				+ Dialogs.getUnspentTxUrls(cnt) + "<br/>"
				+ Dialogs.read("email_send_receipt_code_content1", locale) + " <b>" + code + "</b>";
		t.setPreview(content1);
		t.setContent1(content1);
		t.setContent2(Dialogs.read("email_content2", locale) + cnt.getValue("id"));
		t.setSalutation(Dialogs.read("email_salutation", locale));
		t.setSupport(Dialogs.read("email_need_support", locale), Dialogs.read("email_contact_us", locale),
				Dialogs.getSupportUrl(cnt.getSource().getName(), cnt.getValue("payer.email")));
		t.setVersion(Config.getValue("DIGIDEAL_VERSION"));
		htmlPart.setText(t.toHtml(), "US-ASCII", "html");

		content.addBodyPart(htmlPart);
		content.addBodyPart(Tools.addImage(qr, code + ".png"));
		email.setContent(content);
		return email;
	}

	private static String genQRGmail(Contract cnt) throws IOException {
		return "googlegmail:///co?subject=" + Dialogs.read("email_subject_prefix", cnt.getSource().getLocale()) + " "
				+ Dialogs.read("email_validate_receipt_code_subject", cnt.getSource().getLocale()) + "&to="
				+ cnt.getValue("agent.email") + "&body=" + cnt.getReceiptCode();
	}

	public static void main(String args[]) {
		/*
		 * try { Contract cnt = new Contract("",
		 * "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
		 * cnt.setUnspentTxId(
		 * "25fb4dc0542b8071cb7150504971e81faf5d3ced86f22e09519fb4080a8c0732"); State
		 * funds = new WaitingFunds(cnt); funds.run();
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

}
