package org.trimatek.digideal.agent;

import java.util.Optional;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Receipt;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;

/*
 * VM arguments: --add-opens org.trimatek.digideal.rules/org.trimatek.digideal.rules=rulebook.core
 */

public class Agent {
	
	public static Receipt validateReceiptCode(Contract cnt, Receipt receipt) {
		RuleBookRunner ruleBook = new RuleBookRunner("org.trimatek.digideal.agent.rules");	
		NameValueReferableMap facts = new FactMap();
		facts.setValue("contract", cnt);
		facts.setValue("receipt", receipt);
		ruleBook.run(facts);
		Optional<Result> o = ruleBook.getResult();
        return (Receipt) o.get().getValue();
	}
	
	

	public static void main(String[] args) {
		
		Contract c = new Contract();
		c.setReceiptCode("123.123");
		
		Receipt r = new Receipt("123123");
		
		Receipt result = Agent.validateReceiptCode(c, r);
		System.out.println("Remito validado: " + result.isValid());
		
		
		/*RuleBookRunner ruleBook = new RuleBookRunner("org.trimatek.digideal.rules");
		NameValueReferableMap facts = new FactMap();
		
		Contract c = new Contract();
		c.setReceiptCode("123.123");
		
		Receipt r = new Receipt("123123");
		r.setImage("la imagen".getBytes());
		System.out.println("Remito validado: " + r.isValid());
		
		facts.setValue("receipt", r);
		facts.setValue("contract", c);
		ruleBook.run(facts);
		//ruleBook.getResult().ifPresent(); // prints "Hello World"
		
	    Optional<Result> o = ruleBook.getResult();
	        
	    Receipt res = (Receipt) o.get().getValue();
	    System.out.println("Remito validado: " + res.isValid());
*/
	}

}
