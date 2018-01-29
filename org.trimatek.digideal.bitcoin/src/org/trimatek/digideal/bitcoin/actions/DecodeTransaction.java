package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.entities.Context;
import org.trimatek.digideal.bitcoin.entities.Contract;
import org.trimatek.digideal.bitcoin.tools.Translators;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class DecodeTransaction extends Action {

	public Contract exec(Contract contract) throws IOException {

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run DecodeTransaction for " + contract.getValue("contract.id"));
		Process pr = rt.exec(Context.PATH_TO_CLI + buildParams(contract));

		String err = Translators.toString(pr.getErrorStream());
		String in = Translators.toString(pr.getInputStream());

		if (err.isEmpty()) {
			logger.log(Level.INFO, "Execution success");
			JsonObject json = new Gson().fromJson(in, JsonObject.class);
			JsonArray vouts = json.getAsJsonArray("vout");
			for (JsonElement e : vouts) {
				JsonObject o = e.getAsJsonObject();
				BigDecimal sent = o.get("value").getAsBigDecimal();
				BigDecimal sts = new BigDecimal(contract.getValue("sts"));
				if (sent.compareTo(sts) == 0) {
					contract.setUnspentVout(o.get("n").getAsInt());
					JsonObject scriptPubKey = o.getAsJsonObject("scriptPubKey");
					contract.setUnspentOutputScript(scriptPubKey.get("hex").getAsString());
					done = Boolean.TRUE;
					return contract;
				} 
			}			
		} else {
			logger.log(Level.INFO, "Execution failed");
		}
		return null;
	}

	private static String buildParams(Contract cnt) throws IOException {
		return " decoderawtransaction " + cnt.getUnspentRaw();
	}

}
