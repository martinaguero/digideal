package org.trimatek.digideal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.trimatek.digideal.comm.rest.Server;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Launcher;
import org.trimatek.digideal.repo.Repository;
import org.trimatek.digideal.workflow.Workflow;

public class Start implements Launcher {

	private ScheduledExecutorService exe;
	private Server server;

	public void init() {
		
		startServer();
		startWorkflow();
		
		
	}
	
	private void startServer() {
		
		server = new Server();
		server.init();
		
	}
	
	private void startWorkflow() {
		// Contract cnt = new Contract("","D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\QUINTO.properties");
		// cnt.setRequiredSignatures(1);

		Contract cnt = Repository.getInstance().loadContract(27);
		exe = Executors.newScheduledThreadPool(5);
		Workflow wf = new Workflow(cnt);

		ScheduledFuture<?> future = exe.scheduleAtFixedRate(wf, 0, 20, TimeUnit.SECONDS);

		while (!exe.isShutdown()) {
			if (future.isDone()) {
				future.cancel(false);
			}
		}
	}

	public static void main(String[] args)
			throws FileNotFoundException, IOException, InterruptedException, ExecutionException {

		Start s = new Start();
		s.init();

	}

}
