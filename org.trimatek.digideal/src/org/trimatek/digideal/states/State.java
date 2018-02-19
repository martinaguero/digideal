package org.trimatek.digideal.states;

import java.util.LinkedList;

import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;

public abstract class State {

	private State current;
	protected Contract contract;
	protected LinkedList<Action> pending = new LinkedList<Action>();
	protected LinkedList<Action> completed = new LinkedList<Action>();

	public void run() throws Exception {
		while (!pending.isEmpty()) {
			Action action = pending.removeFirst();
			if (!action.done) {
				Contract result = action.exec(contract);
				contract = result != null ? result : contract;
				completed.add(action);
			}
		}
	}

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
