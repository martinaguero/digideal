package org.trimatek.digideal.states;

import org.trimatek.digideal.actions.CheckTxEmail;
import org.trimatek.digideal.model.Contract;

public class WaitingFunds extends State {

	public WaitingFunds(Contract contract) {
		this.contract = contract;
		pending.add(new CheckTxEmail());
	}

}
