package org.trimatek.digideal.actions;

import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;

import javax.mail.Message;

import org.trimatek.digideal.Config;
import org.trimatek.digideal.comm.mail.LoadMessages;
import org.trimatek.digideal.comm.mail.ModifyMessageLabel;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Receipt;
import org.trimatek.digideal.model.State;
import org.trimatek.digideal.tools.Mail;
import org.trimatek.digideal.workflow.WaitingReceipt;

public class CheckReceiptCode extends Action {

	@Override
	public Contract exec(Contract cnt) throws Exception {

		String code = null;
		String msgId = null;

		logger.log(Level.INFO, "Ready to retrieve receipt code from mailbox");
		List<Message> messages = (List<Message>) LoadMessages
				.exec("is:unread from:(" + cnt.getValue("payer.email") + ") to:(" + cnt.getValue("agent.email") + ")");

		for (Message message : messages) {
			code = parseReceiveCode(Mail.getMessageTextContent(message));
			if (code != null) {
				msgId = message.getHeader(Config.MAIL_ID)[0];
				break;
			} /* Analiza los adjuntos
			else {
				Map<String, Object> att = Mail.getAttachments(message);
				for (Map.Entry<String, Object> entry : att.entrySet()) {
					if (isValidExt(entry.getKey())) {
						code = parseReceiveCode(Generators.readQRCode(entry.getValue()));
						if (code != null) {
							msgId = message.getHeader(Config.MAIL_ID)[0];
							break;
						}
					}
				}
			}*/
		}
		
		if (code != null && !code.equals("")) {
			ModifyMessageLabel.exec(msgId, "INBOX", "UNREAD");
			logger.log(Level.INFO, "Receipt code found: " + code);
			cnt.setReceipt(new Receipt(code));
			done = Boolean.TRUE;
			return cnt;
		}
		logger.log(Level.INFO, "Receipt code not found");
		return null;
	}

	private static String parseReceiveCode(String content) {
		content = content.replace(" ", System.lineSeparator());
		String[] words = content.split(System.lineSeparator());
		for (String w : words) {
			if (Pattern.matches(Config.RECEIPT_CODE_REGEX, w)) {
				return w;
			}
		}
		return null;
	}

	private boolean isValidExt(String fileName) {
		fileName = fileName.toLowerCase().substring(fileName.lastIndexOf(".")+1);
		return Config.VALID_QR_EXT.contains(fileName);
	}

	public static void main(String args[]) {

		try {
			Contract cnt = new Contract("", "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
			cnt.setUnspentTxId("25fb4dc0542b8071cb7150504971e81faf5d3ced86f22e09519fb4080a8c0732");
			State delivery = new WaitingReceipt(cnt);
			delivery.run();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
