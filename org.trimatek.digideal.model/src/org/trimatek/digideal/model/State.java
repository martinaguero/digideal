package org.trimatek.digideal.model;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class State {

	private State current;
	protected Contract contract;
	protected LinkedList<Action> pending = new LinkedList<Action>();
	protected LinkedList<Action> completed = new LinkedList<Action>();
	protected final static Logger logger = Logger.getLogger(State.class.getName());

	public void run() throws Exception {
		logger.log(Level.INFO,"############# Running in " + this.getClass().getSimpleName().toUpperCase() + " state #############");
		while (!pending.isEmpty()) {
			Action action = pending.removeFirst();
			if (!action.done) {
				Contract result = action.exec(contract);
				if(result!=null) {
					contract = result;
					completed.add(action);
				} else {
					pending.addFirst(action);
					return;
				}				
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

	public boolean isCompleted() {
		return pending.size() == 0 && completed.size() != 0 ? true : false;
	}
	
	public abstract String getNextName() throws Exception;

}
