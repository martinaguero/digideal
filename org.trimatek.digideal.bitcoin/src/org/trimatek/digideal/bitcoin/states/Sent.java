package org.trimatek.digideal.bitcoin.states;

import java.util.LinkedList;

import org.trimatek.digideal.bitcoin.actions.Action;
import org.trimatek.digideal.bitcoin.actions.CreateTransaction;
import org.trimatek.digideal.bitcoin.actions.DecodeTransaction;
import org.trimatek.digideal.bitcoin.actions.GetUnspentRaw;
import org.trimatek.digideal.bitcoin.actions.RequestTxSignature;
import org.trimatek.digideal.bitcoin.entities.Contract;

public class Sent extends State {

	private LinkedList<Action> pending = new LinkedList<Action>();
	private LinkedList<Action> completed = new LinkedList<Action>();

	public Sent(Contract contract) {
		this.contract = contract;
		pending.add(new GetUnspentRaw());
		pending.add(new DecodeTransaction());
		pending.add(new CreateTransaction());
		pending.add(new RequestTxSignature());
	}

	public void run() throws Exception {
		while (!pending.isEmpty()) {
			Action action = pending.removeFirst();
			if (!action.done) {
				Contract result = action.exec(contract);
				contract = result != null ? result : contract;
				completed.add(action);
			}
		}
	}
	
	public static void main(String args[]){
		
		try {
			Contract cnt = new Contract("", "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
			cnt.setUnspentTxId("aaca37aae04c977842505831e32487ee8d82ff4a46682cf3e066cfe6d7204aeb");
			State sent = new Sent(cnt);			
			sent.run();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
