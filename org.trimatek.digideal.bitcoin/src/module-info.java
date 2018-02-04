module org.trimatek.digideal.bitcoin {
	exports org.trimatek.digideal.bitcoin.entities;
	exports org.trimatek.digideal.bitcoin.tools;
	exports org.trimatek.digideal.bitcoin.actions;
	exports org.trimatek.digideal.bitcoin.states;
	exports org.trimatek.digideal.bitcoin.workflow;

	requires gson;
	requires java.logging;
	requires sendgrid.java.latest;
	requires org.trimatek.digideal.model;
	requires java.sql;
}