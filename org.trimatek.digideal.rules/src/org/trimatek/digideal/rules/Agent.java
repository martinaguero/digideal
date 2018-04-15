package org.trimatek.digideal.rules;

import java.util.Optional;

import org.trimatek.digideal.model.Contract;
import org.trimatek.digideal.model.Remito;

import com.deliveredtechnologies.rulebook.FactMap;
import com.deliveredtechnologies.rulebook.NameValueReferableMap;
import com.deliveredtechnologies.rulebook.Result;
import com.deliveredtechnologies.rulebook.model.runner.RuleBookRunner;

/*
 * VM arguments: --add-opens org.trimatek.digideal.rules/org.trimatek.digideal.rules=rulebook.core
 */

public class Agent {

	public static void main(String[] args) {
		RuleBookRunner ruleBook = new RuleBookRunner("org.trimatek.digideal.rules");
		NameValueReferableMap facts = new FactMap();
		
		Contract c = new Contract();
		c.setReceiptCode("123");
		
		Remito r = new Remito();
		r.setImage("la imagen".getBytes());
		r.setCode("123");
		System.out.println("Remito validado: " + r.isValid());
		
		facts.setValue("remito", r);
		facts.setValue("contract", c);
		ruleBook.run(facts);
		//ruleBook.getResult().ifPresent(); // prints "Hello World"
		
	    Optional<Result> o = ruleBook.getResult();
	        
	    Remito res = (Remito) o.get().getValue();
	    System.out.println("Remito validado: " + res.isValid());

	}

}
