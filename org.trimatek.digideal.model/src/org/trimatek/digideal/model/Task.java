package org.trimatek.digideal.model;

import java.io.IOException;

public interface Task {
	
	public Task with(Object target);
	
	public Transaction exec(Transaction transaction) throws InterruptedException, IOException;

}
