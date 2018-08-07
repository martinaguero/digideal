package org.trimatek.digideal.bitcoin.actions.tx;

import java.io.IOException;

import org.trimatek.digideal.model.Task;
import org.trimatek.digideal.model.Transaction;

public class RetrieveAndMatchTask implements Task {

	private String address;
	
	public Transaction exec(Transaction tx) throws IOException, InterruptedException {
		tx = new GetTxRaw().exec(tx);
		tx = tx != null ? new DecodeTxRaw().exec(tx) : null;
		tx = tx != null ? new GetTxVinAddress().exec(tx) : null;
		return tx != null && tx.getVinAddress().equalsIgnoreCase(address) ? tx : null;
	}

	public RetrieveAndMatchTask with(Object address) {
		this.address = ((String)address).trim();
		return this;
	}
	
}
