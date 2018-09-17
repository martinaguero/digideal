package org.trimatek.digideal.compiler.actions;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.Translators;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.utils.Config;
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
			String sourcePath = Config.getValue("COCO_TEMP") + contract.getSource().getName();

			FileWriter writer = new FileWriter(sourcePath + Config.getValue("COCO_SRC_EXT"));
			writer.write(contract.getSource().getText());
			writer.close();

			if (Files.exists(Paths.get(sourcePath + Config.getValue("COCO_SRC_EXT")))) {
				Process pr = rt.exec(buildParams(contract.getSource().getLocale(), sourcePath));
				err = Translators.toString(pr.getErrorStream());
				res = Translators.toString(pr.getInputStream());
			}
			if (err.isEmpty()) {
				logger.log(Level.INFO, "Execution success: source compiled");
				contract.setInstructions(sourcePath + Config.getValue("COCO_COMPILED_EXT"));
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

	private static String buildParams(String locale, String sourcePath) throws IOException {
		String lang = locale.contains("_") ? locale.substring(locale.indexOf("_")) : "_" + locale;
		return Config.getValue("COCO_JRE") + " -jar " + Config.getValue("COCO_PATH") + lang + ".jar " + sourcePath
				+ Config.getValue("COCO_SRC_EXT");
	}

}
