package org.trimatek.digideal.bitcoin.actions.tx;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.ReadStream;
import org.trimatek.digideal.model.Transaction;
import org.trimatek.digideal.model.TxAction;
import org.trimatek.digideal.model.utils.Config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class DecodeTxRaw extends TxAction {

	public Transaction exec(Transaction tx) throws IOException, InterruptedException {

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run DecodeTxRaw for " + tx.getTxId());
		Process pr = rt.exec(Config.getValue("BTC_PATH_TO_CLI") + buildParams(tx));

		ReadStream s1 = new ReadStream("stdin", pr.getInputStream());
		ReadStream s2 = new ReadStream("stderr", pr.getErrorStream());
		s1.start();
		s2.start();
		pr.waitFor();

		String in = s1.getStream();
		String err = s2.getStream();

		if (err != null && err.isEmpty()) {
			JsonObject json = new Gson().fromJson(in, JsonObject.class);
			JsonArray vouts = json.getAsJsonArray("vin");
			for (JsonElement e : vouts) {
				JsonObject o = e.getAsJsonObject();
				JsonObject scriptPubKey = o.getAsJsonObject("scriptSig");
				JsonPrimitive asm = scriptPubKey.getAsJsonPrimitive("asm");
				if (asm!=null) {
					tx.setScriptSigAsm(asm.toString());
					success = Boolean.TRUE;
					return tx;
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

}
