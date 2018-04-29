package org.trimatek.digideal.port;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.repo.Repository;
import org.trimatek.digideal.workflow.Workflow;

public class Launcher {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
		
//		Contract cnt = new Contract("","D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\CUARTO.properties");
//		cnt.setRequiredSignatures(1);
		
		Contract cnt = Repository.getInstance().loadContract(6);
		
		ScheduledExecutorService exe = Executors.newScheduledThreadPool(5);
		
		Workflow wf = new Workflow(cnt);
		
		ScheduledFuture<?> future = exe.scheduleAtFixedRate(wf, 0, 20 ,TimeUnit.SECONDS);
			
		while(!exe.isShutdown()) {
			if(future.isDone()) {
				future.cancel(false);				
			}		
		}

	}

}
