package org.trimatek.digideal.bitcoin.states;

import org.trimatek.digideal.bitcoin.entities.Contract;

public abstract class State {

	private State current;
	protected Contract contract;

	public abstract void run() throws Exception;

	public State getCurrent() {
		return current;
	}

	public void setCurrent(State state) {
		current = state;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
