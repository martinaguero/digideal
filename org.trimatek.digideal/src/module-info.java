/**
 * 
 */
/**
 * @author Martín
 *
 */
module org.trimatek.digideal {
	exports org.trimatek.digideal.tools;

	requires zxing;
	requires java.logging;
	requires org.trimatek.digideal.model;
	requires org.trimatek.digideal.bitcoin;
	requires org.trimatek.digideal.repo;
	requires sendgrid.java.latest;	
	requires java.sql;
}