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
import org.trimatek.digideal.model.State;
import org.trimatek.digideal.tools.Mail;
import org.trimatek.digideal.workflow.WaitingFunds;

public class CheckTxEmail extends Action {

	@Override
	public Contract exec(Contract cnt) throws Exception {
		String tx = null;
		String msgId = null;
		logger.log(Level.INFO, "Ready to retrieve Pay Tx from mailbox");
		List<Message> messages = (List<Message>) LoadMessages
				.exec("is:unread from:(" + cnt.getValue("payer.email") + ") to:(" + cnt.getValue("agent.email") + ")");
		for (Message message : messages) {
			tx = getTx(Mail.getMessageTextContent(message));			
			if(tx != null) {
				cnt.setUnspentTxId(tx);
				msgId = message.getHeader(Config.MAIL_ID)[0];
				break;
			}
		}
		if (tx != null && !tx.equals("")) {
			ModifyMessageLabel.exec(msgId,"INBOX","UNREAD");
			logger.log(Level.INFO, "Pay Tx found");
			done = Boolean.TRUE;
			return cnt;
		}
		logger.log(Level.INFO, "Pay Tx not found");
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

	public static void main(String args[]) {
/*
		try {
			Contract cnt = new Contract("", "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
			cnt.setUnspentTxId("25fb4dc0542b8071cb7150504971e81faf5d3ced86f22e09519fb4080a8c0732");
			State funds = new WaitingFunds(cnt);
			funds.run();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
