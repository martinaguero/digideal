package org.trimatek.digideal.workflow;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.State;
import org.trimatek.digideal.repo.Repository;

public class Workflow implements Runnable {

	protected final static Logger logger = Logger.getLogger(Workflow.class.getName());
	private Contract cnt;

	private Workflow() {
	}

	public Workflow(Contract cnt) {
		this.cnt = cnt;
	}

	@Override
	public void run() {
		State state = null;

		try {
			if (cnt.getStatusName() == null) {
				state = new New(cnt);
				state.run();
			} else {
				state = loadState(cnt);
				state.run();
			}
			if (state.isCompleted()) {
				cnt.setStatusName(state.getNextName());
				Repository.getInstance().save(cnt);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage()); 			
		}
		if (Done.class.isInstance(state)) {
			cnt.setRunning(Boolean.FALSE);
			Repository.getInstance().save(cnt);
			Thread.currentThread().stop();
		}

	}

	private State loadState(Contract cnt) throws Exception {
		Class stClass = Class.forName("org.trimatek.digideal.workflow." + cnt.getStatusName());
		Constructor constr = stClass.getConstructor(Contract.class);
		return (State) constr.newInstance(cnt);
	}

	public static void main(String[] args) {


	}

	/*
	 * New -> WaitingFunds -> FundsAvailable -> WaitingReceipt -> Received
	 * 
	 * 
	 */

}
