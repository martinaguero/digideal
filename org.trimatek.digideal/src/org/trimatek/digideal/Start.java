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
		logger.log(Level.INFO, "Shutting down complete");
	}

	private void startREST() {
		restServer = new Server(Integer.parseInt(Config.getValue("REST_SERVER_PORT")));
		restServer.init();
	}

	private void startWorkflow() {
		logger.log(Level.INFO,
				"Starting workflow runner with a capacity of " + Config.getValue("WF_CAPACITY")
						+ " simultaneous contracts and a reload rate of " + Config.getValue("WF_RELOAD_RATE_MIN") + " "
						+ TimeUnit.MINUTES);
		exe = Executors.newScheduledThreadPool(1);
		wfRunner = new WorkflowRunner();
		exe.scheduleAtFixedRate(wfRunner, 0, Long.parseLong(Config.getValue("WF_RELOAD_RATE_MIN")), TimeUnit.MINUTES);
		logger.log(Level.INFO, "DigiDeal ROBOT ready");
	}

	public static void main(String[] args)
			throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
		logger.log(Level.INFO,
				"////////////// DigiDeal v." + Config.getValue("DIGIDEAL_VERSION") + " \\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		logger.log(Level.INFO, "Ready logging");
		Start s = new Start();
		s.init();
		System.out.println("DigiDeal v." + Config.getValue("DIGIDEAL_VERSION") + " started on port "
				+ Config.getValue("REST_SERVER_PORT") + ".");
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				logger.log(Level.INFO, "Workflow Runner is shutting down");
				System.out.println("DigiDeal shutting down.");
				try {
					s.shutdown();
					System.out.println("DigiDeal stopped.");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	class WorkflowRunner implements Runnable {

		ScheduledExecutorService exe = Executors
				.newScheduledThreadPool(Integer.parseInt(Config.getValue("WF_CAPACITY")));
		// ScheduledFuture<Workflow> future = null;

		@Override
		public void run() {

			// if (future != null && future.isDone()) {
			// future.cancel(false);
			// }

			List<Contract> undone = Repository.getInstance().loadUndoneContracts();
			logger.log(Level.INFO, "Round undone contracts: " + undone.size());

			for (Contract cnt : undone) {
				cnt.setRunning(Boolean.TRUE);
				Repository.getInstance().save(cnt);
				Workflow wf = new Workflow(cnt);
				exe.scheduleAtFixedRate(wf, 0, Long.parseLong(Config.getValue("WF_RERUN_RATE_SEC")), TimeUnit.SECONDS);
			}

		}

		public void stop() {
			exe.shutdown();
			try {
				if (!exe.awaitTermination(800, TimeUnit.MILLISECONDS)) {
					exe.shutdownNow();
				}
			} catch (InterruptedException e) {
				exe.shutdownNow();
			}
			int updated = Repository.getInstance().setRunningFalse();
			logger.log(Level.INFO, updated + " contracts stopped");
		}

	}

}
