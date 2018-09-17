package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.ReadStream;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;
import org.trimatek.digideal.model.utils.Config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DecodeTransaction extends Action {

	public Contract exec(Contract contract) throws IOException, InterruptedException {

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run DecodeTransaction for " + contract.getValue("id"));
		Process pr = rt.exec(Config.getValue("BTC_PATH_TO_CLI") + buildParams(contract.getLastUnspentTransaction()));

		ReadStream s1 = new ReadStream("stdin", pr.getInputStream());
		ReadStream s2 = new ReadStream("stderr", pr.getErrorStream());
		s1.start();
		s2.start();
		pr.waitFor();

		String in = s1.getStream();
		String err = s2.getStream();

		if (err != null && err.isEmpty()) {
			logger.log(Level.INFO, "Execution success");
			JsonObject json = new Gson().fromJson(in, JsonObject.class);
			JsonArray vouts = json.getAsJsonArray("vout");
			for (JsonElement e : vouts) { 
				JsonObject o = e.getAsJsonObject();
				JsonObject scriptPubKey = o.getAsJsonObject("scriptPubKey");
				JsonArray addresses = scriptPubKey.getAsJsonArray("addresses");
				for (JsonElement a : addresses) {
					if(compare(a.toString(),contract.getMultisigAddress())){
						Transaction tx = contract.removeLastUnspentTransaction();
						tx.setValue(o.get("value").getAsBigDecimal());
						tx.setVout(o.get("n").getAsInt());
						tx.setOutputScript(scriptPubKey.get("hex").getAsString());
						contract.addUnspentTransaction(tx);
						done = Boolean.TRUE;
						return contract;						
					}
				}			
			}
		} else {
			logger.log(Level.INFO, "Execution failed");
		}
		return null;
	}

	private static String buildParams(Transaction tx) throws IOException {
		return " decoderawtransaction " + tx.getRaw();
	}
	
	private static boolean compare(String fromJson, String multisigAddress) {
		fromJson = fromJson.replace("\"", "");
		return fromJson.equals(multisigAddress);
	}

}
