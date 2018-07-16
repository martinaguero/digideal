package org.trimatek.digideal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
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
		
		//Contract cnt = Repository.getInstance().loadContract(5);
		
		ScheduledFuture<?> future = null;
		exe = Executors.newScheduledThreadPool(10);
		List<Contract> undone = Repository.getInstance().loadUndoneContracts();
		
		for (Contract cnt : undone) {
			Workflow wf = new Workflow(cnt);
			future = exe.scheduleAtFixedRate(wf, 0, Config.SCHEDULER_RATE_SEC, TimeUnit.SECONDS);
		}
		
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
