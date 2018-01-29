package org.trimatek.digideal.bitcoin.workflow;

import org.trimatek.digideal.bitcoin.entities.Contract;
import org.trimatek.digideal.bitcoin.states.New;
import org.trimatek.digideal.bitcoin.states.State;

public class Workflow {

	public static void main(String[] args) {

		try {

			Contract cnt = new Contract("", "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
			State newState = new New(cnt);
			newState.run();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
