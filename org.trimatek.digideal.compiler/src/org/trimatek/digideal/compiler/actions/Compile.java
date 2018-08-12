package org.trimatek.digideal.compiler.actions;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.Translators;
import org.trimatek.digideal.compiler.Context;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.repo.Repository;

public class Compile extends Action implements Runnable {

	private Contract contract;

	@Override
	public void run() {

		try {
			String err = null;
			String res = null;

			Runtime rt = Runtime.getRuntime();
			logger.log(Level.INFO, "Ready to compile source");
			String sourcePath = Context.PATH_TO_TEMP + contract.getSource().getName();

			FileWriter writer = new FileWriter(sourcePath + Context.SOURCE_EXT);
			writer.write(contract.getSource().getText());
			writer.close();

			if (Files.exists(Paths.get(sourcePath + Context.SOURCE_EXT))) {
				Process pr = rt.exec(buildParams(sourcePath));
				err = Translators.toString(pr.getErrorStream());
				res = Translators.toString(pr.getInputStream());
			}
			if (err.isEmpty()) {
				logger.log(Level.INFO, "Execution success: source compiled");
				contract.setInstructions(sourcePath + Context.COMPILED_EXT);
				contract.setStatusName("New");
				contract.setRunning(Boolean.FALSE);
				contract.setRequiredSignatures(1);
				contract.setBtc(new BigDecimal(contract.getValue("btc")));
				Repository.getInstance().save(contract);
				done = Boolean.TRUE;

			} else {
				logger.log(Level.SEVERE, err);
				logger.log(Level.INFO, "Execution failed: source could not be compiled");
				contract = null;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error while compiling source. Message: " + e.getMessage());
		}

	}

	@Override
	public Contract exec(Contract contract) {
		this.contract = contract;
		Thread thread1 = new Thread(this);
		thread1.start();
		return this.contract;
	}

	private static String buildParams(String sourcePath) throws IOException {
		return Context.PATH_TO_JRE8 + " -jar " + Context.PATH_TO_COCO + " " + sourcePath + Context.SOURCE_EXT;
	}

}
