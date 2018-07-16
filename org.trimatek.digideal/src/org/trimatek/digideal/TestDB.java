package org.trimatek.digideal;

import java.util.List;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.repo.Repository;

public class TestDB {

	public static void main(String[] args) {
		List<Contract> contracts = Repository.getInstance().loadUndoneContracts();
		
		for (Contract contract : contracts) {
			System.out.println(contract.getId());
		}

	}

}
