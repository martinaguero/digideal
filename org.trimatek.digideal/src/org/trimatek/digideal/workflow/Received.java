package org.trimatek.digideal.workflow;

import org.trimatek.digideal.actions.NotifyContractSuccess;
import org.trimatek.digideal.bitcoin.actions.SendTransaction;
import org.trimatek.digideal.bitcoin.actions.SignTransaction;
import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.State;

public class Received extends State {
	
	public Received(Contract cnt){
		contract = cnt;
		pending.add(new SignTransaction());
		pending.add(new SendTransaction());
		pending.add(new NotifyContractSuccess());
	}
	
	public String getNextName() {
		return "Done";
	}

	public static void main(String args[]) {
/*
		try {
			Contract cnt = new Contract("", "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
			cnt.addPayTransaction(new Transaction("",
					"020000000132078c0a08b49f51092ef286ed3c5daf1fe87149505071cb71802b54c04dfb250100000000ffffffff02c044f505000000001976a914b79919b17b61770c937b79d9bb15c6a753ae580d88ac204e0000000000001976a914358dc3f2926456e7d2e58a2a4a20c4ddac20789e88ac00000000"));
			cnt.addPayTransaction(new Transaction("",
					"020000000132078c0a08b49f51092ef286ed3c5daf1fe87149505071cb71802b54c04dfb250100000000ffffffff02c044f505000000001976a914b79919b17b61770c937b79d9bb15c6a753ae580d88ac204e0000000000001976a914358dc3f2926456e7d2e58a2a4a20c4ddac20789e88ac00000000"));
			cnt.setRequiredSignatures(1);
			State signed = new Received(cnt);
			signed.run();
			System.out.println("Agent Private Key " + signed.getContract().getAgentPrivateKey());
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
