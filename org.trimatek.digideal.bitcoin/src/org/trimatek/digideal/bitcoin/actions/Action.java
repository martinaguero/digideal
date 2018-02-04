package org.trimatek.digideal.bitcoin.actions;

import java.util.logging.Logger;

import org.trimatek.digideal.model.Contract;



public abstract class Action {

	protected final static Logger logger = Logger.getLogger(Action.class.getName());

	public boolean done = Boolean.FALSE;

	public abstract Contract exec(Contract contract) throws Exception;

}
