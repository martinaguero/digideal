package org.trimatek.digideal.workflow;

import org.trimatek.digideal.actions.SendReceiptCode;
import org.trimatek.digideal.bitcoin.actions.CreateTransaction;
import org.trimatek.digideal.bitcoin.actions.DecodeTransaction;
import org.trimatek.digideal.bitcoin.actions.GetUnspentRaw;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.State;


public class FundsAvailable extends State {

	public FundsAvailable(Contract contract) {
		this.contract = contract;
		pending.add(new GetUnspentRaw());
		pending.add(new DecodeTransaction());
		pending.add(new CreateTransaction());
		//pending.add(new RequestTxSignature()); borrar
		//enviar id de transacción de pago al vendedor
		pending.add(new SendReceiptCode());
	}
	
	public String getNextName() {
		return "WaitingReceipt";
	}
	
	public static void main(String args[]){
		
		try {
			Contract cnt = new Contract("", "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
			cnt.setUnspentTxId("0224f0086973cd185318d9c66796d4400e4937e84fcbd20fed3b9614d3e05ce5");
			State sent = new FundsAvailable(cnt);			
			sent.run();
			System.out.println("SpentTxRaw= " + sent.getContract().getLastPayTransaction().getRaw());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
