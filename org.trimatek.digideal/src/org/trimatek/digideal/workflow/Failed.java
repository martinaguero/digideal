package org.trimatek.digideal.workflow;

import org.trimatek.digideal.actions.NotifyCompilationFailure;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.State;

public class Failed extends State {

	public Failed(Contract contract) {
		this.contract = contract;
		pending.add(new NotifyCompilationFailure());
	}
	
	public String getNextName() {
		return "Done";
	}

}
