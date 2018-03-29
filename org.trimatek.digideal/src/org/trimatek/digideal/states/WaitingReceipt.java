package org.trimatek.digideal.states;

import org.trimatek.digideal.actions.CheckReceiptCode;
import org.trimatek.digideal.model.Contract;

public class WaitingReceipt extends State {

	public WaitingReceipt(Contract contract) {
		this.contract = contract;
		pending.add(new CheckReceiptCode());
		//agregar la validación del código
	}

}
