package org.trimatek.digideal.agent.rules;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Receipt;

import com.deliveredtechnologies.rulebook.RuleState;
import com.deliveredtechnologies.rulebook.annotation.Given;
import com.deliveredtechnologies.rulebook.annotation.Result;
import com.deliveredtechnologies.rulebook.annotation.Rule;
import com.deliveredtechnologies.rulebook.annotation.Then;
import com.deliveredtechnologies.rulebook.annotation.When;

@Rule
public class ValidateReceiptCodeRule {

	@Given("receipt")
	private Receipt receipt;

	@Given("contract")
	private Contract contract;

	@Result
	private Receipt result;

	@When
	public boolean when() {
		return receipt != null && contract != null
				&& receipt.getCode().replace(".", "").equals(contract.getReceiptCode().replace(".", ""));
	}

	@Then
	public RuleState then() {
		result = receipt;
		result.setValid(Boolean.TRUE);
		return RuleState.BREAK;
	}

}
