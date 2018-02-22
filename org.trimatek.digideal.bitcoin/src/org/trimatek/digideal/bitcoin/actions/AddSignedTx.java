package org.trimatek.digideal.bitcoin.actions;

import java.util.logging.Level;

import org.trimatek.digideal.model.AsyncAction;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;



public class AddSignedTx extends AsyncAction<Transaction> {

	@Override
	public Contract exec(Contract contract, Transaction t) throws Exception {
		if (t.getSignedBy() != null && t.getRaw() != null) {
			contract.addPayTransaction(t);
			done = Boolean.TRUE;
			logger.log(Level.INFO, "Transaction pushed to stack");
			return contract;
		}
		logger.log(Level.WARNING, "Transaction could not be pushed to stack");
		return null;
	}

	@Override
	public Contract exec(Contract contract) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
