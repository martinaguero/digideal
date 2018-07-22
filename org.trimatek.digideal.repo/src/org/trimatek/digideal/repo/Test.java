package org.trimatek.digideal.repo;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;

public class Test {

	public static void main(String[] args) {
		
		Contract c = Repository.getInstance().loadContract(1);
		
		//c.addUnspentTransaction(new Transaction("id"));
		
		Transaction tx = c.removeLastUnspentTransaction();
		tx.setOutputScript("script-nuevo");
		
		c.addUnspentTransaction(tx);
		
		for (Transaction t : c.getUnspentTransactions()) {
			System.out.println(t.getId());
		}
		
		Repository.getInstance().save(c);

	}

}
