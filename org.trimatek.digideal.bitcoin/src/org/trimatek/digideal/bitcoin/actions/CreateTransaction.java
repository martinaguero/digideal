package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.Calc;
import org.trimatek.digideal.bitcoin.tools.Translators;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;
import org.trimatek.digideal.model.utils.Config;
import org.trimatek.digideal.model.utils.Tools;

public class CreateTransaction extends Action {

	public Contract exec(Contract contract) throws IOException {

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run CreateTransaction for " + contract.getValue("id"));
		Process pr = rt.exec(Config.getValue("BTC_PATH_TO_CLI") + buildParams(contract));

		String err = Translators.toString(pr.getErrorStream());
		String in = Translators.toString(pr.getInputStream());

		if (err != null && err.isEmpty()) {
			logger.log(Level.INFO, "Execution success");
			contract.addPayTransaction(new Transaction(null, in));
			done = Boolean.TRUE;
			return contract;
		} else {
			logger.log(Level.INFO, "Execution failed");
			logger.log(Level.SEVERE, err);
			return null;
		}
	}

	private static String buildParams(Contract cnt) throws IOException {
		long feeInSts = Calc.calcFee(Calc.calcBytes(1, 2));
		BigDecimal feeInBtc = Calc.satoshiToBtc(feeInSts);
		BigDecimal forCollector = cnt.getBtc().subtract(feeInBtc.add(feeInBtc));
		BigDecimal forAgent = feeInBtc;
		String result;
		if (Tools.isUxHost()) {
			result = " createrawtransaction [" + getUnspents(cnt) + "] {\"" + cnt.getValue("collector.address").trim()
					+ "\":" + forCollector + ",\"" + cnt.getValue("agent.address").trim() + "\":" + forAgent + "}";
		} else {
			result = " createrawtransaction \"[" + getUnspents(cnt) + "]\" \"{\\\"" + cnt.getValue("collector.address")
					+ "\\\":" + forCollector + ",\\\"" + cnt.getValue("agent.address") + "\\\":" + forAgent + "}";
		}
		return result;
	}

	private static String getUnspents(Contract cnt) {
		StringBuilder sb = new StringBuilder();
		int c = 0;
		for (Transaction t : cnt.getUnspentTransactions()) {
			if (c > 0) {
				sb.append(",");
			}
			if (Tools.isUxHost()) {
				sb.append("{\"txid\":\"" + t.getTxId() + "\",\"vout\":" + t.getVout() + "}");
			} else {
				sb.append("{\\\"txid\\\":\\\"" + t.getTxId() + "\\\",\\\"vout\\\":" + t.getVout() + "}");
			}
			c++;
		}
		return sb.toString();
	}

}
