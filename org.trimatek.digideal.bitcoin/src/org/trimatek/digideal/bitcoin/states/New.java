package org.trimatek.digideal.bitcoin.states;

import org.trimatek.digideal.bitcoin.actions.CreateMultisig;
import org.trimatek.digideal.bitcoin.actions.RequestFunds;
import org.trimatek.digideal.model.Contract;

public class New extends State {

	public New(Contract contract) {
		this.contract = contract;
		pending.add(new CreateMultisig());
		pending.add(new RequestFunds());
	}

}
