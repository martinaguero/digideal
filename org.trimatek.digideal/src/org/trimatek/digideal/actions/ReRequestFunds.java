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
import org.trimatek.digideal.comm.mail.Tools;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
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
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);
		BigDecimal dif = calcDifference(cnt);

		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("payer.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject("[DD] New funds request for contract ID: " + cnt.getValue("id"));

		MimeMultipart content = new MimeMultipart("related");
		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setText(
				"<html><body><p>Have been received BTC " + cnt.getLastUnspentTransaction().getValue()
						+ " Please send BTC " + dif + " left to address: <br/>" + cnt.getMultisigAddress() + "\n"
						+ "in order to proceed with contract requirements. <br/><br/>"
						+ "Then, please reply this message with the transaction ID.\" + \"</p><br/>"
						+ "<div style=\"display:none;\"> " + cnt.getValue("id") + " </div></body></html>",
				"US-ASCII", "html");

		content.addBodyPart(htmlPart);
		byte[] qr = Generators.genQRCodeImage(Generators.genQRSendTo(cnt, dif), Config.TAMANIO_QR, Config.TAMANIO_QR);
		content.addBodyPart(Tools.addImage(qr, cnt.getValue("id") + ".png"));
		email.setContent(content);

		return email;
	}

	private static BigDecimal calcDifference(Contract cnt) throws IOException {
		return new BigDecimal(cnt.getValue("btc")).subtract(Calc.addTransactions(cnt.getUnspentTransactions()));
	}

	private static String genQRSendTo(Contract cnt, BigDecimal amount) throws IOException {
		return "bitcoin:" + cnt.getMultisigAddress() + "?amount=" + amount;
	}

}
