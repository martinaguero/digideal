package org.trimatek.digidata;

import org.trimatek.digidata.trading.Repository;
import org.trimatek.digidata.trading.model.Strategy;

public class TestRepo {

	public static void main(String[] args) {
		Repository r = Repository.getInstance();

		 Strategy s = new Strategy();
		 s.setActive(false);
		 s.setName("Cross");
		 s.setVersion("0.7.0");
		 r.save(s);

		// Strategy s = r.loadStrategy(1.0F);
		//
		// System.out.println("ID=" + s.getId() + " VERSION=" + s.getVersion() + "
		// ACTIVE=" + s.isActive());

//		Trade t = new Trade();
//		t.setDate(new Date());
//		t.setResult(new BigDecimal(102.01));
//		t.setConnection("FXCM");
//		t.setInstrument("EURUSD");
//		t.setSession("1115");
//		t.setAccount("ACC");
//		
//		r.save(t);
		
//		localhost:8282/digidata/trading/add?session=11&account=ACC&connection=FXCM&instrument=EURUSD&strategy=cross/0.7.0&result=90
//		localhost:8282/digidata/strategy/active?name=cross&version=1.0
	}

}
