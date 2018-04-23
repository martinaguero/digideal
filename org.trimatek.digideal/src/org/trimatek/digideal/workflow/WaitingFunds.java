package org.trimatek.digideal.workflow;

import org.trimatek.digideal.actions.CheckTxEmail;
import org.trimatek.digideal.actions.SendReceiptCode;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.State;

public class WaitingFunds extends State {

	public WaitingFunds(Contract contract) {
		this.contract = contract;
		pending.add(new CheckTxEmail());		
	}
	
	public String getNextName() {
		return "FundsAvailable";
	}

}
