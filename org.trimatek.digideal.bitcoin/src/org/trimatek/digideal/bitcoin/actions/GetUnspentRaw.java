package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.entities.Context;
import org.trimatek.digideal.bitcoin.entities.Contract;
import org.trimatek.digideal.bitcoin.tools.Translators;

public class GetUnspentRaw extends Action {

	public Contract exec(Contract contract) throws IOException {

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run GetUnspentRaw for " + contract.getValue("contract.id"));
		Process pr = rt.exec(Context.PATH_TO_CLI + buildParams(contract));

		String err = Translators.toString(pr.getErrorStream());
		String in = Translators.toString(pr.getInputStream());

		if (err.isEmpty()) {
			logger.log(Level.INFO, "Execution success");
			contract.setUnspentRaw(in);
			done = Boolean.TRUE;
			return contract;
		} else {
			logger.log(Level.INFO, "Execution failed");
			return null;
		}
	}

	private static String buildParams(Contract cnt) throws IOException {
		return " getrawtransaction " + cnt.getUnspentTxId();
	}

}
