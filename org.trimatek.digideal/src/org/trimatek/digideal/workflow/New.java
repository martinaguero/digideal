package org.trimatek.digideal.workflow;

import org.trimatek.digideal.actions.RequestFunds;
import org.trimatek.digideal.bitcoin.actions.CreateMultisig;
import org.trimatek.digideal.bitcoin.actions.GetNewAddress;
import org.trimatek.digideal.bitcoin.actions.ImportAddress;
import org.trimatek.digideal.bitcoin.actions.ValidateAddress;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.State;

public class New extends State {

	public New(Contract contract) {
		this.contract = contract;
		pending.add(new GetNewAddress());
//		pending.add(new ValidateAddress());
		pending.add(new CreateMultisig());
		pending.add(new RequestFunds());
		pending.add(new ImportAddress());
	}
	
	public String getNextName() {
		return "WaitingFunds";
	}

}
