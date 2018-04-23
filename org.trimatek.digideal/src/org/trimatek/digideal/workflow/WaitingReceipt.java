package org.trimatek.digideal.workflow;

import org.trimatek.digideal.actions.CheckReceiptCode;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.State;

public class WaitingReceipt extends State {

	public WaitingReceipt(Contract contract) {
		this.contract = contract;
		pending.add(new CheckReceiptCode());
		//agregar la validaci�n del c�digo
	}
	
	public String getNextName() {
		return "Received";
	}

}
