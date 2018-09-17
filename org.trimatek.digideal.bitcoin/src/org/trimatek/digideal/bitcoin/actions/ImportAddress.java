package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.Translators;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.utils.Config;

public class ImportAddress extends Action {

	public Contract exec(Contract contract) throws IOException {

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run ImportAddress for " + contract.getValue("id"));
		Process pr = rt.exec(Config.getValue("BTC_PATH_TO_CLI") + buildParams(contract));

		String err = Translators.toString(pr.getErrorStream());
		String in = Translators.toString(pr.getInputStream());

		if (err.isEmpty()) {
			logger.log(Level.INFO, "Execution success");
			done = Boolean.TRUE;
			return contract;
		} else {
			logger.log(Level.INFO, "Execution failed: " + err);
			return null;
		}
	}

	private static String buildParams(Contract cnt) throws IOException {
		return " -named importaddress address=" + cnt.getMultisigAddress() + " rescan=false";
	}

}
