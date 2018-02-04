package org.trimatek.digideal.bitcoin.actions;

import java.util.logging.Logger;

import org.trimatek.digideal.model.Contract;



public abstract class AsyncAction<E> {

	protected final static Logger logger = Logger.getLogger(AsyncAction.class.getName());

	public boolean done = Boolean.FALSE;

	public abstract Contract exec(Contract contract, E elem) throws Exception;

}
