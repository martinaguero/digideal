/**
 * 
 */
/**
 * @author Mart�n
 *
 */
module org.trimatek.digideal {
	exports org.trimatek.digideal.tools;

	requires zxing;
	requires java.logging;
	requires org.trimatek.digideal.model;
	requires org.trimatek.digideal.bitcoin;
	requires sendgrid.java.latest;	
	requires java.sql;
}