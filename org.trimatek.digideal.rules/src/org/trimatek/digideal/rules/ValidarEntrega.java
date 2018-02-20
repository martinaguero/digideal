package org.trimatek.digideal.rules;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Remito;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

@Rule
public class ValidarEntrega {

	@Given("remito")
	private Remito remito;

	@Given("contract")
	private Contract contract;

	@Result
	private Remito result;

	@When
	public boolean when() {
		return remito.getImage() != null && remito.getCode() == contract.getDeliveryCode() ? true : false;
	}

	@Then
	public RuleState then() {
		result = remito;
		result.setValid(Boolean.TRUE);
		return RuleState.BREAK;
	}
}
