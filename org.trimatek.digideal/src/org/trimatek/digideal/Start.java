package org.trimatek.digideal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.trimatek.digideal.comm.rest.Server;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Launcher;
import org.trimatek.digideal.model.utils.Config;
import org.trimatek.digideal.repo.Repository;
import org.trimatek.digideal.workflow.Workflow;

public class Start implements Launcher {

	private static Logger logger;
	private ScheduledExecutorService exe;
	private WorkflowRunner wfRunner;
	private Server restServer;

	static {
		InputStream inputStream = Start.class.getResourceAsStream("/logging.properties");
		if (null != inputStream) {
			try {
				LogManager.getLogManager().readConfiguration(inputStream);
				logger = Logger.getLogger(Start.class.getCanonicalName());
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, "Logging system failed", e);
			}
		}
	}

	public void init() {
		startREST();
		startWorkflow();
	}

	private void shutdown() throws InterruptedException {
		wfRunner.stop();
	}

	private void startREST() {
		restServer = new Server(Integer.parseInt(Config.getValue("REST_SERVER_PORT")));
		restServer.init();
	}

	private void startWorkflow() {
		logger.log(Level.INFO, "Starting Workflow Runner");
		exe = Executors.newScheduledThreadPool(1);
		wfRunner = new WorkflowRunner();
		exe.scheduleAtFixedRate(wfRunner, 0, 1, TimeUnit.MINUTES);
		logger.log(Level.INFO, "Workflow Runner reload rate " + 1 + " " + TimeUnit.MINUTES);
	}

	public static void main(String[] args)
			throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
		logger.log(Level.INFO,
				"////////////// DigiDeal v." + Config.getValue("DIGIDEAL_VERSION") + " \\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		logger.log(Level.INFO, "Ready logging");
		Start s = new Start();
		s.init();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				logger.log(Level.INFO, "Workflow Runner is shutting down");
				try {
					s.shutdown();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.log(Level.INFO, "Shutting down finished");
			}
		});
	}

	class WorkflowRunner implements Runnable {

		ScheduledExecutorService exe = Executors.newScheduledThreadPool(10);
		List<Long> loaded = new ArrayList<Long>();
		// ScheduledFuture<Workflow> future = null;

		@Override
		public void run() {

			// if (future != null && future.isDone()) {
			// future.cancel(false);
			// }

			logger.log(Level.INFO, "Loading undone contracts");
			List<Contract> undone = Repository.getInstance().loadUndoneContracts();
			logger.log(Level.INFO, "Round undone contracts: " + undone.size());

			for (Contract cnt : undone) {
				cnt.setRunning(Boolean.TRUE);
				Repository.getInstance().save(cnt);
				loaded.add(cnt.getId());
				Workflow wf = new Workflow(cnt);
				exe.scheduleAtFixedRate(wf, 0, 20, TimeUnit.SECONDS);
			}

		}

		public void stop() {
			for (long id : loaded) {
				Repository.getInstance().setRunningFalse(id);
			}
		}

	}

}
