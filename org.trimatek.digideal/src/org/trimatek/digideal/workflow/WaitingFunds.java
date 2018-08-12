package org.trimatek.digideal.workflow;

import java.math.BigDecimal;

import org.trimatek.digideal.actions.ReRequestFunds;
import org.trimatek.digideal.bitcoin.actions.DecodeTransaction;
import org.trimatek.digideal.bitcoin.actions.GetUnspentRaw;
import org.trimatek.digideal.bitcoin.actions.ListUnspent;
import org.trimatek.digideal.bitcoin.tools.Calc;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.State;

public class WaitingFunds extends State {

	public WaitingFunds(Contract contract) {
		this.contract = contract;
		pending.add(new ListUnspent());
//		pending.add(new CheckTxEmail());
		pending.add(new GetUnspentRaw());
		pending.add(new DecodeTransaction());
	}

	public String getNextName() throws Exception {
		BigDecimal sum = Calc.addTransactions(contract.getUnspentTransactions());
		int res = sum.compareTo(contract.getBtc());
		if (res < 0) {
			new ReRequestFunds().exec(contract);
			return "WaitingFunds";
		}
		return "FundsAvailable";
	}

}
