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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ListUnspent extends Action {

	public Contract exec(Contract contract) throws IOException, InterruptedException {
		int min = Integer.parseInt(Config.getValue("BC_UNSPENT_MIN_CONF"));
		int MAX_CONF = Integer.parseInt(Config.getValue("BC_UNSPENT_MAX_CONF"));
		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run ListUnspent for " + contract.getValue("id"));

		while (min != MAX_CONF) {
			int max = min + Integer.parseInt(Config.getValue("BC_UNSPENT_DELTA_CONF"));
			Process pr = rt
					.exec(Config.getValue("BTC_PATH_TO_CLI") + buildParams(contract.getMultisigAddress(), min, max));
/*
			ReadStream s1 = new ReadStream("stdin", pr.getInputStream());
			ReadStream s2 = new ReadStream("stderr", pr.getErrorStream());
			s1.start();
			s2.start();
			pr.waitFor();

			String in = s1.getStream();
			String err = s2.getStream();
*/
			String err = Translators.toString(pr.getErrorStream());
			String in = Translators.toString(pr.getInputStream());
			
			if (err != null && err.isEmpty()) {
				JsonArray txs = new Gson().fromJson(in, JsonArray.class);
				logger.log(Level.INFO, "Execution success. In confirmations rage [" + min + "," + max + "] retrieved "
						+ txs.size() + " transactions.");
				for (JsonElement t : txs) {
					JsonObject o = t.getAsJsonObject();
					String txid = o.getAsJsonPrimitive("txid").toString().replace("\"", "");
					if (!Tools.contains(txid, contract.getUnspentTransactions())) {
						Transaction tx = new Transaction(txid);
						contract.addUnspentTransaction(tx);
						logger.log(Level.INFO, "1 unspent transaction found.");
						done = Boolean.TRUE;
						return contract;
					}
				}
			} else {
				logger.log(Level.SEVERE, err);
			}
			min = min + Integer.parseInt(Config.getValue("BC_UNSPENT_DELTA_CONF"));
		}
		logger.log(Level.INFO, "0 unspent transaction found.");
		return null;

	}

	private static String buildParams(String multisig, int minconf, int maxConf) throws IOException {
		return Tools.isUxHost() ? " listunspent " + minconf + " " + maxConf + " [\"" + multisig + "\"]"
				: " listunspent " + minconf + " " + maxConf + " \"[\\\"" + multisig + "\\\"]\"";
	}

	public static void main(String args[]) throws IOException, InterruptedException {
		Contract c = new Contract(null, "D:\\Temp\\digideal\\71IULOO.properties");
		c.setMultisigAddress("2MvLksDGh9xxBME1do621AXpkeYoRKVYbiq");
		ListUnspent lu = new ListUnspent();
		c = lu.exec(c);
		if (c != null) {
			System.out.println("OK!!!!!");
		}

	}

}
