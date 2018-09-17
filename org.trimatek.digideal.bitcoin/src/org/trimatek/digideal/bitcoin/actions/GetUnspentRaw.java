package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.Translators;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;
import org.trimatek.digideal.model.utils.Config;

public class GetUnspentRaw extends Action {

	public Contract exec(Contract contract) throws IOException {

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run GetUnspentRaw for " + contract.getValue("id"));
		Process pr = rt.exec(Config.getValue("BTC_PATH_TO_CLI") + buildParams(contract.getLastUnspentTransaction()));

		String err = Translators.toString(pr.getErrorStream());
		String in = Translators.toString(pr.getInputStream());

		if (err.isEmpty()) {
			logger.log(Level.INFO, "Execution success");
			Transaction tx = contract.removeLastUnspentTransaction();			
			tx.setRaw(in);			
			contract.addUnspentTransaction(tx);
			done = Boolean.TRUE;
			return contract;
		} else {			
			logger.log(Level.WARNING, err);			
			return null;
		}
	}

	private static String buildParams(Transaction t) throws IOException {
		return " getrawtransaction " + t.getTxId();
	}

}
