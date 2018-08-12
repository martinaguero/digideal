package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.entities.Context;
import org.trimatek.digideal.bitcoin.tools.ReadStream;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ValidateAddress extends Action {

	public Contract exec(Contract contract) throws IOException, InterruptedException {

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run ValidateAddress for " + contract.getValue("id"));
		Process pr = rt.exec(Context.PATH_TO_CLI + buildParams(contract));

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
			JsonPrimitive pubkey = json.getAsJsonPrimitive("pubkey");
			//contract.setPublicKey(pubkey.toString().replace("\"", ""));
			done = Boolean.TRUE;
			return contract;			
		} else {
			logger.log(Level.INFO, "Execution failed");
		}
		return null;
	}

	private static String buildParams(Contract cnt) throws IOException {
		return " validateaddress " + cnt.getAddress();
	}


}
