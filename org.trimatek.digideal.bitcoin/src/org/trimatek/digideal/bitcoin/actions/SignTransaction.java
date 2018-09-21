package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.Translators;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;
import org.trimatek.digideal.model.utils.Config;
import org.trimatek.digideal.model.utils.Tools;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class SignTransaction extends Action {

	public Contract exec(Contract contract) throws IOException {

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run SignTransaction for " + contract.getValue("id"));
		Process pr = rt.exec(Config.getValue("BTC_PATH_TO_CLI") + buildParams(contract));

		String err = Translators.toString(pr.getErrorStream());
		String in = Translators.toString(pr.getInputStream());

		if (err.isEmpty()) {
			logger.log(Level.INFO, "Execution success");
			JsonObject json = new Gson().fromJson(in, JsonObject.class);
			contract.addPayTransaction(
					new Transaction(contract.getValue("agent.address"), json.get("hex").getAsString()));
			done = Boolean.TRUE;
			return contract;
		} else {
			logger.log(Level.INFO, "Execution failed");
			logger.log(Level.SEVERE, err);
			return null;
		}
	}

	private static String buildParams(Contract cnt) throws IOException {
		String result;
		if (Tools.isUxHost()) {
			result = " signrawtransaction " + cnt.getLastPayTransaction().getRaw() + " [" + getUnspents(cnt) + "]"
					+ " [\"" + cnt.getPrivateKey() + "\"]";
		} else {
			result = " signrawtransaction " + cnt.getLastPayTransaction().getRaw() + " \"[" + getUnspents(cnt) + "]\""
					+ " \"[\\\"" + cnt.getPrivateKey() + "\\\"]";
		}
		return result;
	}

	private static String getUnspents(Contract cnt) {
		StringBuilder sb = new StringBuilder();
		int c = 0;
		for (Transaction tx : cnt.getUnspentTransactions()) {
			if (c > 0) {
				sb.append(",");
			}
			if (Tools.isUxHost()) {
				sb.append("{\"txid\":\"" + tx.getTxId() + "\",\"vout\":" + tx.getVout()
				+ ",\"scriptPubKey\":\"" + tx.getOutputScript() + "\",\"redeemScript\":\""
				+ cnt.getRedeemScript() + "\"}");
			} else {
				sb.append("{\\\"txid\\\":\\\"" + tx.getTxId() + "\\\",\\\"vout\\\":" + tx.getVout()
						+ ",\\\"scriptPubKey\\\":\\\"" + tx.getOutputScript() + "\\\",\\\"redeemScript\\\":\\\""
						+ cnt.getRedeemScript() + "\\\"}");
			}
			c++;
		}
		return sb.toString();
	}

}
