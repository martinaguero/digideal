package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.Translators;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.utils.Config;

public class SendTransaction extends Action {

	@Override
	public Contract exec(Contract contract) throws Exception {

		if (contract.countPayTransactions() < contract.getRequiredSignatures()) {
			logger.log(Level.SEVERE, "Minimum required signatures not reached");
			return null;
		}

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run SendTransaction for " + contract.getValue("id"));
		Process pr = rt.exec(Config.getValue("BTC_PATH_TO_CLI") + buildParams(contract));

		String err = Translators.toString(pr.getErrorStream());
		String in = Translators.toString(pr.getInputStream());

		if (err.isEmpty()) {
			logger.log(Level.INFO, "Execution success");
			contract.setSpentTxId(in);
			done = Boolean.TRUE;
			return contract;
		} else {
			logger.log(Level.SEVERE, "Execution failed, transaction could not be send. Error: " + err);
		}

		return null;
	}

	private static String buildParams(Contract cnt) throws IOException {
		return " sendrawtransaction " + cnt.getLastPayTransaction().getRaw();	
	}

}
