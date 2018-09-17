package org.trimatek.digideal;

import java.util.List;

public class Context {

//	public static final String DIGIDEAL_VERSION = "0.1.0";
	public static final String TX_REGEX = "[a-fA-F0-9]{64}";
	public static final String RECEIPT_CODE_REGEX = "([0-9][0-9][0-9])(\\.)?([A-Z]|[a-z]){3}(\\.)?([0-9][0-9][0-9])";
	public static final String MAIL_ID = "Gmail-ID";
	public static final int TAMANIO_QR = 350;
	public static final List<String> VALID_QR_EXT = List.of("jpg","png","jpeg","bmp","gif");
	public static final int SCHEDULER_RATE_SEC = 20;
	public static final int RELOAD_CONTRACTS_RATE_MIN = 1;
	public final static String MESSAGES_BUNDLE = "org.trimatek.digideal.messages";
//	public final static String CONTACT_US_URL = "http://localhost/org.trimatek.digideal.ui/faces/support.xhtml";
//	public static final int REST_SERVER_PORT = 9090;

}
