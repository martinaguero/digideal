package org.trimatek.digideal.bitcoin.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

import org.trimatek.digideal.bitcoin.entities.Context;
import org.trimatek.digideal.model.Transaction;

public class Calc {

	public static int calcBytes(int inputs, int outputs) {
		return ((inputs * 148) + (outputs * 34) + 10);
	}

	public static BigDecimal satoshiToBtc(Long sts) {
		return new BigDecimal(sts).divide(new BigDecimal(Context.COIN_VALUE), Context.DEFAULT_SCALE,
				RoundingMode.CEILING);
	}

	public static int calcFee(int bytes) {
		return bytes * Context.STS_PER_BYTE;
	}
	
	public static BigDecimal addTransactions(Set<Transaction> unspents) {
		BigDecimal result = new BigDecimal(0);
		for (Transaction t : unspents) {
			result = result.add(t.getValue());
		}
		return result;
	}

}
