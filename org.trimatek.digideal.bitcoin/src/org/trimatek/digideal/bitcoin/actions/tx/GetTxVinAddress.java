package org.trimatek.digideal.bitcoin.actions.tx;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.entities.Context;
import org.trimatek.digideal.bitcoin.tools.Crypto;
import org.trimatek.digideal.model.Transaction;
import org.trimatek.digideal.model.TxAction;

public class GetTxVinAddress extends TxAction {

	@Override
	public Transaction exec(Transaction tx) throws IOException, InterruptedException {

		String[] asm = tx.getScriptSigAsm().split(" ");
		String address = Crypto.getInstance().toAddress(asm[1].replace("\"",""), Context.MAINNET);

		if (address != null) {
			tx.setVinAddress(address);
			success = Boolean.TRUE;
			return tx;
		} else {
			logger.log(Level.WARNING, "Could not retrieve address from public key");
			return null;
		}
	}

}
