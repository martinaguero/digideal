package org.trimatek.digideal.workflow;

import org.trimatek.digideal.actions.NotifyFundsAvailability;
import org.trimatek.digideal.actions.SendReceiptCode;
import org.trimatek.digideal.bitcoin.actions.CreateTransaction;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.State;


public class FundsAvailable extends State {

	public FundsAvailable(Contract contract) {
		this.contract = contract;		
		pending.add(new NotifyFundsAvailability());
		pending.add(new CreateTransaction());
		pending.add(new SendReceiptCode());
	}
	
	public String getNextName() {
		return "WaitingReceipt";
	}
	
	public static void main(String args[]){
	}
	
	

}
