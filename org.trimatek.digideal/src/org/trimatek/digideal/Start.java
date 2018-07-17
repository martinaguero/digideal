package org.trimatek.digideal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.trimatek.digideal.comm.rest.Server;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Launcher;
import org.trimatek.digideal.repo.Repository;
import org.trimatek.digideal.workflow.Workflow;

public class Start implements Launcher {

	protected final static Logger logger = Logger.getLogger(Start.class.getName());
	
	private Server server;

	public void init() {
		
		startREST();
		startWorkflow();
		
		
	}
	
	private void startREST() {
		
		server = new Server();
		server.init();
		
	}
	
	private void startWorkflow() {
		
		logger.log(Level.INFO, "Starting Workflow Runner");
		ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
		
		exe.scheduleAtFixedRate(new WorkflowRunner(), 0, 1, TimeUnit.MINUTES);
		logger.log(Level.INFO, "Workflow Runner reload rate " + 1 + " " + TimeUnit.MINUTES);
		
	}

	public static void main(String[] args)
			throws FileNotFoundException, IOException, InterruptedException, ExecutionException {

		Start s = new Start();
		s.init();

	}
	
	class WorkflowRunner implements Runnable {
		
		ScheduledExecutorService exe = Executors.newScheduledThreadPool(10);
		ScheduledFuture<?> future = null;
		
		@Override
		public void run() {
			
			if (future != null && future.isDone()) {
				future.cancel(false);
			}
			
			logger.log(Level.INFO, "Loading undone contracts");
			List<Contract> undone = Repository.getInstance().loadUndoneContracts();
			logger.log(Level.INFO, "Round undone contracts: " + undone.size()); 
			
			for (Contract cnt : undone) {
				cnt.setRunning(Boolean.TRUE);
				Repository.getInstance().save(cnt);
				Workflow wf = new Workflow(cnt);
				future = exe.scheduleAtFixedRate(wf, 0, 20, TimeUnit.SECONDS);
			}
			
		}
		
		
		
	}

}
