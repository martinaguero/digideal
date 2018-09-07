package org.trimatek.digidata;

public class Config {
	
	public static final String BTC_USD_RATE_BCINFO_URL = "https://blockchain.info/tobtc?currency=USD&value=1";
	public static final String BTC_FEE_PREDICTION_EARN_URL = "https://bitcoinfees.earn.com/api/v1/fees/recommended";
	public final static int BTC_FEE_MINUTES_TO_UPDATE = 30;
	public final static int BTC_FEE_HOURS_TO_UPDATE_HISTORIC = 2;
	public final static int BTC_RATE_HOURS_UPDATE = 1;
	public final static float BTC_USD_RATE_LAST = 0.00013744f;
	
}
