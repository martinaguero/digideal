package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.entities.Context;
import org.trimatek.digideal.bitcoin.tools.Translators;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;

public class DumpPrivateKey extends Action {

	@Override
	public Contract exec(Contract contract) throws Exception {
		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run DumpPrivateKey for " + contract.getValue("id"));
		Process pr = rt.exec(Context.PATH_TO_CLI + buildParams(contract.getAddress()));

		String err = Translators.toString(pr.getErrorStream());
		String in = Translators.toString(pr.getInputStream());

		if (err.isEmpty()) {
			logger.log(Level.INFO, "Execution success");
			contract.setPrivateKey(in);
			done = Boolean.TRUE;
			return contract;
		} else {
			logger.log(Level.INFO, "Execution failed");
			logger.log(Level.SEVERE, err);
			return null;
		}
	}

	private static String buildParams(String address) throws IOException {
		return " dumpprivkey " + address;

	}

}
