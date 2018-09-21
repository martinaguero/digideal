package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.Translators;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.utils.Config;
import org.trimatek.digideal.model.utils.Tools;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CreateMultisig extends Action {

	public Contract exec(Contract contract) throws IOException {

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run CreateMultisig for " + contract.getValue("id"));
		Process pr = rt.exec(Config.getValue("BTC_PATH_TO_CLI") + buildParams(contract, contract.getRequiredSignatures()));
		
		String err = Translators.toString(pr.getErrorStream());
		String in = Translators.toString(pr.getInputStream());

		if (err.isEmpty()) {
			logger.log(Level.INFO, "Execution success");
			JsonObject json = new Gson().fromJson(in, JsonObject.class);
			contract.setMultisigAddress(json.get("address").getAsString());
			contract.setRedeemScript(json.get("redeemScript").getAsString());
			done = Boolean.TRUE;
			return contract;
		} else {
			logger.log(Level.SEVERE, "Execution failed: " + err);
			return null;
		}
	}

	private static String buildParams(Contract cnt, int minReqSig) throws IOException {
		return Tools.isUxHost() ? " createmultisig " + minReqSig + " [\"" + cnt.getAddress() + "\"]"
				: " createmultisig " + minReqSig + " \"[\\\"" + cnt.getAddress() + "\\\"]\"";
	}

}
