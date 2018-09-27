package org.trimatek.digideal.ui;

import java.text.SimpleDateFormat;

public class Config {
	
	public final static String REQUIRED_FIELD = "background-color:#ffffcc;";
	public final static String AGENT_ADDRESS = "mkQ7wFkReXVXTNQpi57zwTATY4kFG6KuPF";
	public final static String AGENT_NICK = "@digideal";
	public final static String AGENT_EMAIL = "digideal.services@gmail.com";
	public final static String GOOGLE_GEO_API_KEY = "AIzaSyAcLmRFJceRjP-Wkg8NQ2XOm-6cvML8A8E";
	public final static String MESSAGES_BUNDLE = "org.trimatek.digideal.ui.messages";
	public final static String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public final static String NAME_REGEX = "^[^\\d]+$";
	public final static String BTC_ADDRESS_REGEX = "^[123mn][1-9A-HJ-NP-Za-km-z]{26,35}";
	public final static String NAVIGATION_RESULT = "result";
	public final static String NAVIGATION_INDEX = "main";
	public final static String BTC_PRICE_URL = "http://trimatek.org:8282/digidata/rate/btc/usd";
//	public final static String DIGIDEAL_SOURCE_URL = "http://localhost:9090/api/sources/";
//	public final static String DIGIDEAL_TICKET_URL = "http://localhost:9090/api/tickets/";
	public final static String SERIAL_URL = "http://trimatek.org:8282/digidata/serial/";
	public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public final static String DIGIDEAL_SOURCE_URL = "http://190.210.244.92:9090/api/sources/";
	public final static String DIGIDEAL_TICKET_URL = "http://190.210.244.92:9090/api/tickets/";

}
