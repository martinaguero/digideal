package org.trimatek.digideal.bitcoin.states;

import org.trimatek.digideal.bitcoin.actions.CreateTransaction;
import org.trimatek.digideal.bitcoin.actions.DecodeTransaction;
import org.trimatek.digideal.bitcoin.actions.GetUnspentRaw;
import org.trimatek.digideal.bitcoin.actions.RequestTxSignature;
import org.trimatek.digideal.model.Contract;


public class Sent extends State {

	public Sent(Contract contract) {
		this.contract = contract;
		pending.add(new GetUnspentRaw());
		pending.add(new DecodeTransaction());
		pending.add(new CreateTransaction());
		pending.add(new RequestTxSignature());
	}
	
	public static void main(String args[]){
		
		try {
			Contract cnt = new Contract("", "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
			cnt.setUnspentTxId("aaca37aae04c977842505831e32487ee8d82ff4a46682cf3e066cfe6d7204aeb");
			State sent = new Sent(cnt);			
			sent.run();
			System.out.println("SpentTxRaw= " + sent.getContract().getpayTx().getRaw());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
