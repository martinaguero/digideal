package org.trimatek.digideal.bitcoin.actions.tx;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.Crypto;
import org.trimatek.digideal.model.Transaction;
import org.trimatek.digideal.model.TxAction;
import org.trimatek.digideal.model.utils.Config;

public class GetTxVinAddress extends TxAction {

	@Override
	public Transaction exec(Transaction tx) throws IOException, InterruptedException {

		String[] asm = tx.getScriptSigAsm().split(" ");
		String address = Crypto.getInstance().toAddress(asm[1].replace("\"", ""),
				Boolean.parseBoolean(Config.getValue("MAINNET")));

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
