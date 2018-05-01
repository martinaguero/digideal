module org.trimatek.digideal.comm {
	exports org.trimatek.digideal.comm.mail;

	requires org.trimatek.digideal.model;
	requires google.api.client;
	requires google.api.services.gmail.v1.rev82;
	requires google.http.client;
	requires google.http.client.jackson2;
	requires google.oauth.client;
	requires google.oauth.client.java6;
	requires google.oauth.client.jetty;
	requires java.mail;
	requires java.base;
	requires java.activation;
	requires java.logging;
	requires vertx.core;
	requires vertx.web;
	requires gson;
	requires java.sql;
}