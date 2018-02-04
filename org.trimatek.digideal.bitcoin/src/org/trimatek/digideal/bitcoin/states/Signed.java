package org.trimatek.digideal.bitcoin.states;

import org.trimatek.digideal.bitcoin.actions.SendTransaction;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Transaction;


public class Signed extends State {
	
	public Signed(Contract cnt){
		contract = cnt;
		pending.add(new SendTransaction());
	}

	public static void main(String args[]) {

		try {
			Contract cnt = new Contract("", "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
			cnt.pushPayTx(new Transaction("",
					"0200000001eb4a20d7e6cf66e0f32c68464aff828dee8724e33158504278974ce0aa37caaa0100000000ffffffff02c044f505000000001976a914b79919b17b61770c937b79d9bb15c6a753ae580d88ac204e0000000000001976a914358dc3f2926456e7d2e58a2a4a20c4ddac20789e88ac00000000"));
			cnt.setRequiredSignatures(2);
			State signed = new Signed(cnt);
			signed.run();
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
