package org.trimatek.digideal.actions;

import java.util.Properties;
import java.util.logging.Level;
import java.util.regex.Pattern;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.trimatek.digideal.Config;
import org.trimatek.digideal.comm.mail.SendMessage;
import org.trimatek.digideal.comm.mail.Tools;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.tools.Generators;
import org.trimatek.digideal.workflow.State;
import org.trimatek.digideal.workflow.WaitingFunds;

public class SendReceiptCode extends Action {

	@Override
	public Contract exec(Contract cnt) throws Exception {
		
		logger.log(Level.INFO, "Ready to send receive code");
		cnt.setReceiptCode(Generators.genNewDeliveryCode());
		byte[] qr = Generators.genQRCodeImage(cnt.getReceiptCode(), Config.TAMANIO_QR, Config.TAMANIO_QR);
		Object result = SendMessage.exec(setupMail(cnt,qr,cnt.getReceiptCode()));

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
			if (Pattern.matches(Config.TX_REGEX, w)) {
				return w;
			}
		}
		return null;
	}
	
	private static MimeMessage setupMail(Contract cnt, byte[] qr, String code) throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(cnt.getValue("agent.email"), "DigiDeal"));
		InternetAddress[] to = InternetAddress.parse(cnt.getValue("payer.email"));
		email.setRecipients(RecipientType.TO, to);
		email.setSubject("[DD] Receive code");
		
		MimeMultipart content = new MimeMultipart("related");
		
		MimeBodyPart htmlPart = new MimeBodyPart();
		htmlPart.setText(""
		  + "<html>"
		  + " <body>"
		  + "  <p><b>" +code+ "</b></p>"
		  + " </body>"
		  + "</html>" 
		  ,"US-ASCII", "html");
		content.addBodyPart(htmlPart);
		content.addBodyPart(Tools.addImage(qr, code + ".png"));
		email.setContent(content);
		return email;	
	}

	public static void main(String args[]) {

		try {
			Contract cnt = new Contract("", "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
			cnt.setUnspentTxId("25fb4dc0542b8071cb7150504971e81faf5d3ced86f22e09519fb4080a8c0732");
			State funds = new WaitingFunds(cnt);
			funds.run();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
