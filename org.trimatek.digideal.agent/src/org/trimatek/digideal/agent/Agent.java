package org.trimatek.digideal.agent;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Receipt;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;

/*
 * VM arguments: --add-opens org.trimatek.digideal.agent/org.trimatek.digideal.agent.rules=rulebook.core
 */

public class Agent {

	public static Receipt validateReceiptCode(Contract cnt, Receipt receipt) {
		RuleBookRunner ruleBook = new RuleBookRunner("org.trimatek.digideal.agent.rules");
		NameValueReferableMap facts = new FactMap();
		facts.setValue("contract", cnt);
		facts.setValue("receipt", receipt);
		ruleBook.run(facts);
		Object o = ruleBook.getResult().get().getValue();
		return Receipt.class.isInstance(o) ? (Receipt) o : null;
	}

	public static void main(String[] args) {

		Contract c = new Contract();
		c.setReceiptCode("123.ABC.456");

		Receipt r = new Receipt("123ABC456");

		Receipt result = Agent.validateReceiptCode(c, r);
		if (result != null) {
			System.out.println("Remito validado: " + result.isValid());
		}

		/*
		 * RuleBookRunner ruleBook = new
		 * RuleBookRunner("org.trimatek.digideal.agent.rules"); NameValueReferableMap
		 * facts = new FactMap();
		 * 
		 * Contract c = new Contract(); c.setReceiptCode("123.123");
		 * 
		 * Receipt r = new Receipt("123123"); r.setImage("la imagen".getBytes());
		 * System.out.println("Remito validado: " + r.isValid());
		 * 
		 * facts.setValue("receipt", r); facts.setValue("contract", c);
		 * ruleBook.run(facts); // ruleBook.getResult().ifPresent(); // prints
		 * "Hello World"
		 * 
		 * Optional<Result> o = ruleBook.getResult().isPresent() ? ruleBook.getResult()
		 * : null;
		 * 
		 * Receipt res = (Receipt) o.get().getValue();
		 * System.out.println("Remito validado: " + res.isValid());
		 */
	}

}
