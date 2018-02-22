package org.trimatek.digideal.bitcoin.actions;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.entities.Context;
import org.trimatek.digideal.bitcoin.tools.Translators;
import org.trimatek.digideal.model.Action;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;


public class SignTransaction extends Action {

	public Contract exec(Contract contract) throws IOException {

		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run SignTransaction for " + contract.getValue("id"));
		Process pr = rt.exec(Context.PATH_TO_CLI + buildParams(contract));

		String err = Translators.toString(pr.getErrorStream());
		String in = Translators.toString(pr.getInputStream());

		if (err.isEmpty()) {
			logger.log(Level.INFO, "Execution success");
			System.out.println(in);
			//
			// falta parsear el in para obtener el raw de la transacción firmada
			//contract.pushPayTx(new Transaction(contract.getValue("agent.name"), in));
			done = Boolean.TRUE;
			return contract;
		} else {
			logger.log(Level.INFO, "Execution failed");
			logger.log(Level.SEVERE, err);
			return null;
		}
	}

	private static String buildParams(Contract cnt) throws IOException {
		return " signrawtransaction " + cnt.getLastPayTransaction().getRaw() + " \"[{\\\"txid\\\":\\\""
				+ cnt.getUnspentTxId() + "\\\",\\\"vout\\\":" + cnt.getUnspentVout() + ",\\\"scriptPubKey\\\":\\\""
				+ cnt.getUnspentOutputScript() + "\\\",\\\"redeemScript\\\":\\\"" + cnt.getRedeemScript() + "\\\"}]\""
				+ " \"[\\\"" + cnt.getAgentPrivateKey() + "\\\"]";		
	}

}
