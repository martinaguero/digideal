package org.trimatek.digideal.services;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.states.New;
import org.trimatek.digideal.states.State;


public class Workflow {

	public static void main(String[] args) {

		try {

			Contract cnt = new Contract("", "D:\\Dropbox\\Criptomonedas\\digideal\\contrato\\ABC.properties");
			cnt.setRequiredSignatures(2);
			State newState = new New(cnt);
			newState.run();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	 * New -> Sent -> Signed
	 * 
	 * 
	 */

}
