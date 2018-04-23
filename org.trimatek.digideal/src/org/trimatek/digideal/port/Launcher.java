package org.trimatek.digideal.port;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.repo.Repository;
import org.trimatek.digideal.workflow.Workflow;

public class Launcher {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
		/*
		Contract cnt = new Contract("","D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
		cnt.setRequiredSignatures(1);
		*/
		Contract cnt = Repository.getInstance().loadContract(4);
		
		ScheduledExecutorService exe = Executors.newScheduledThreadPool(5);
		
		Workflow wf = new Workflow(cnt);
		
		exe.scheduleAtFixedRate(wf, 0, 10 ,TimeUnit.SECONDS);
		

	}

}
