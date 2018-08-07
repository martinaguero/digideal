package org.trimatek.digideal.model;

import java.util.logging.Logger;

public abstract class Action {

	protected final static Logger logger = Logger.getLogger(Action.class.getName());
	public boolean done = Boolean.FALSE;
	private Task task;

	public abstract Contract exec(Contract contract) throws Exception;
	
	public Action() {}
	
	public Action(Task task) {
		this.task = task;
	}
	
	public Task getTask(){
		return task;
	}
	
}
