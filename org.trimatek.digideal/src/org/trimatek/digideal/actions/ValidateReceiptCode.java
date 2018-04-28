package org.trimatek.digideal.actions;

import java.util.logging.Level;

import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Receipt;
import org.trimatek.digideal.agent.Agent;

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
		}
		logger.log(Level.SEVERE, "Receipt code not valid");
		return null;		
	}

public static void main(String[] args) throws Exception {
		
		Contract c = new Contract();
		c.setReceiptCode("123.123");
		Receipt r = new Receipt("123123");
		c.setReceipt(r);
		
		ValidateReceiptCode val = new ValidateReceiptCode();
		c = val.exec(c);
		
		System.out.println("Remito validado: " + c.getReceipt().isValid());

	
	}

}
