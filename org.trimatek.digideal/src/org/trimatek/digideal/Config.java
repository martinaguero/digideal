package org.trimatek.digideal;

import java.util.List;

public class Config {

	public static final String TX_REGEX = "[a-fA-F0-9]{64}";
	public static final String RECEIPT_CODE_REGEX = "^([0-9][0-9][0-9])(\\.)?([A-Z]|[a-z]){3}(\\.)?([0-9][0-9][0-9])$";
	public static final String MAIL_ID = "Gmail-ID";
	public static final int TAMANIO_QR = 350;
	public static final List<String> VALID_QR_EXT = List.of("jpg","png","jpeg","bmp","gif");
	public static final String MAIL_SUBJECT_HEADER = "[DD]";
	public static final int SCHEDULER_RATE_SEC = 20;

}
