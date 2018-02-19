package org.trimatek.digideal.model;

import java.util.logging.Logger;

public abstract class Action {

	protected final static Logger logger = Logger.getLogger(Action.class.getName());

	public boolean done = Boolean.FALSE;

	public abstract Contract exec(Contract contract) throws Exception;

}
