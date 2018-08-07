package org.trimatek.digideal.model;

import java.util.logging.Logger;

public abstract class TxAction {

	protected final static Logger logger = Logger.getLogger(TxAction.class.getName());

	public boolean success = Boolean.FALSE;

	public abstract Transaction exec(Transaction tx) throws Exception;
	
}
