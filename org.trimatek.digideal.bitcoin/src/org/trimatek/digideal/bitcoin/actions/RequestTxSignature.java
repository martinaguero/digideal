package org.trimatek.digideal.bitcoin.actions;

import java.util.logging.Level;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.bitcoin.tools.Mail;

public class RequestTxSignature extends Action {

	@Override
	public Contract exec(Contract cnt) throws Exception {
		
		String body = "<html><body><p>Please sign the current transaction raw: <b><br>" + cnt.getpayTx().getRaw() + "<br>" 
				+ "</b> in order to authorize send STS " + cnt.getValue("sts") + " to @" + cnt.getValue("collector.name")
				+ "</p></body></html>";
		
		String [] to = {cnt.getValue("payer.email"),cnt.getValue("collector.email")};
		int result = Mail.sendMail("[DD] Signature request", body, cnt.getValue("agent.email"), to);
		
		if (String.valueOf(result).charAt(0) == '2') {
			logger.log(Level.INFO, "Message send successfully");
			done = Boolean.TRUE;
			return cnt;
		}
		logger.log(Level.SEVERE, "Message could not be send");
		return null;
	}

}
