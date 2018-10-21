package org.trimatek.digideal.model.utils;

import java.util.ArrayList;
import java.util.List;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Status;
import org.trimatek.digideal.model.Transaction;

public class StatusFactory {

	public static Status build(Contract cnt) {
		Status status = new Status();
		status.setId(cnt.getSource().getName());
		status.setStatus(cnt.getStatusName());
		status.setComments(cnt.getComments());
		status.setMultisigAddress(cnt.getMultisigAddress() != null ? cnt.getMultisigAddress() : "");
		if (cnt.getUnspentTransactions() != null) {
			List<String> txs = new ArrayList<String>();
			for (Transaction tx : cnt.getUnspentTransactions()) {
				txs.add(tx.getTxId());
			}
			status.setUnspentTransactions(txs.stream().toArray(String[]::new));
		}
		return status;
	}

}
