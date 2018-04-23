package org.trimatek.digideal.workflow;

import org.trimatek.digideal.actions.RequestFunds;
import org.trimatek.digideal.bitcoin.actions.CreateMultisig;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.State;

public class New extends State {

	public New(Contract contract) {
		this.contract = contract;
		pending.add(new CreateMultisig());
		pending.add(new RequestFunds());
	}
	
	public String getNextName() {
		return "WaitingFunds";
	}

}
