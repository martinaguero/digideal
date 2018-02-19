module org.trimatek.digideal.bitcoin {
	exports org.trimatek.digideal.bitcoin.entities;
	exports org.trimatek.digideal.bitcoin.tools;
	exports org.trimatek.digideal.bitcoin.actions;

	requires gson;
	requires java.logging;
	requires org.trimatek.digideal.model;
	requires java.sql;
}