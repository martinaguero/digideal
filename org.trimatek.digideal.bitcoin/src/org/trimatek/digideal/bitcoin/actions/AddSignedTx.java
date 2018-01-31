package org.trimatek.digideal.bitcoin.actions;

import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.entities.Contract;
import org.trimatek.digideal.bitcoin.entities.Transaction;

public class AddSignedTx extends AsyncAction<Transaction> {

	@Override
	public Contract exec(Contract contract, Transaction t) throws Exception {
		if (t.getSignedBy() != null && t.getRaw() != null) {
			contract.pushPayTx(t);
			done = Boolean.TRUE;
			logger.log(Level.INFO, "Transaction pushed to stack");
			return contract;
		}
		logger.log(Level.WARNING, "Transaction could not be pushed to stack");
		return null;
	}

}
