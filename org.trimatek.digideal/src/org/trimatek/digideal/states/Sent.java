package org.trimatek.digideal.states;

import org.trimatek.digideal.actions.RequestTxSignature;
import org.trimatek.digideal.bitcoin.actions.CreateTransaction;
import org.trimatek.digideal.bitcoin.actions.DecodeTransaction;
import org.trimatek.digideal.bitcoin.actions.GetUnspentRaw;
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
			cnt.setUnspentTxId("25fb4dc0542b8071cb7150504971e81faf5d3ced86f22e09519fb4080a8c0732");
			State sent = new Sent(cnt);			
			sent.run();
			System.out.println("SpentTxRaw= " + sent.getContract().getpayTx().getRaw());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
