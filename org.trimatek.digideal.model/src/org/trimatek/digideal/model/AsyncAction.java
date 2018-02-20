package org.trimatek.digideal.model;

import java.util.logging.Logger;

public abstract class AsyncAction<E> extends Action {

	protected final static Logger logger = Logger.getLogger(AsyncAction.class.getName());

	public boolean done = Boolean.FALSE;

	public abstract Contract exec(Contract contract, E elem) throws Exception;

}
