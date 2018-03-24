package org.trimatek.digideal.states;

import org.trimatek.digideal.actions.CheckReceiveCode;
import org.trimatek.digideal.model.Contract;

public class WaitingDelivery extends State {

	public WaitingDelivery(Contract contract) {
		this.contract = contract;
		pending.add(new CheckReceiveCode());		
	}

}
