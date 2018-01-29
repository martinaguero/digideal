package org.trimatek.digideal.bitcoin.states;

import java.util.LinkedList;

import org.trimatek.digideal.bitcoin.actions.Action;
import org.trimatek.digideal.bitcoin.actions.CreateMultisig;
import org.trimatek.digideal.bitcoin.actions.RequestFunds;
import org.trimatek.digideal.bitcoin.entities.Contract;

public class New extends State {

	private LinkedList<Action> pending = new LinkedList<Action>();
	private LinkedList<Action> completed = new LinkedList<Action>();

	public New(Contract contract) {
		this.contract = contract;
		pending.add(new CreateMultisig());
		pending.add(new RequestFunds());
	}

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

}
