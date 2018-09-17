package org.trimatek.digideal.bitcoin.actions.tx;

import java.io.IOException;
import java.util.logging.Level;

import org.trimatek.digideal.bitcoin.tools.Translators;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;
import org.trimatek.digideal.model.TxAction;
import org.trimatek.digideal.model.utils.Config;

public class GetTxRaw extends TxAction {

	@Override
	public Transaction exec(Transaction tx) throws IOException, InterruptedException {
		Runtime rt = Runtime.getRuntime();
		logger.log(Level.INFO, "Ready to run GetTxRaw for " + tx.getTxId());
		Process pr = rt.exec(Config.getValue("BTC_PATH_TO_CLI") + buildParams(tx));
		
		String err = Translators.toString(pr.getErrorStream());
		String in = Translators.toString(pr.getInputStream());
		
		if (err.isEmpty()) {
			tx.setRaw(in);
			success = Boolean.TRUE;
			return tx;
		} else {
			logger.log(Level.WARNING, err);
			return null;
		}
	}

	private static String buildParams(Transaction t) throws IOException {
		return " getrawtransaction " + t.getTxId();
	}
	
	public static void main(String args[]) throws Exception {
		
		GetTxRaw txRaw = new GetTxRaw();
		Contract c = new Contract(null, "D:\\Temp\\digideal\\71IULOO.properties");
		Transaction t = new Transaction("0195ff7676b6c19ab2d249e444d735a069baea0bf05c7da3e1cec525c9c67404");
		
		t = txRaw.exec(t);
		
		System.out.println(t.getRaw());
		
		
	}

}
