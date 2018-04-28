package org.trimatek.digideal.workflow;

import org.trimatek.digideal.actions.CheckReceiptCode;
import org.trimatek.digideal.actions.ValidateReceiptCode;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.State;

public class WaitingReceipt extends State {

	public WaitingReceipt(Contract contract) {
		this.contract = contract;
		pending.add(new CheckReceiptCode());
		pending.add(new ValidateReceiptCode());
	}
	
	public String getNextName() {
		return "Received";
	}

}
