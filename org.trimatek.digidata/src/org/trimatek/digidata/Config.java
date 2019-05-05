package org.trimatek.digidata;

public class Config {

	public static final String DIGIDATA_VERSION = "0.27.3-Beta";
	public static final String BTC_USD_RATE_BCINFO_URL = "https://blockchain.info/tobtc?currency=USD&value=1";
	public static final String BTC_BRL_RATE_BCINFO_URL = "https://blockchain.info/tobtc?currency=BRL&value=1";
	public static final String BTC_EUR_RATE_BCINFO_URL = "https://blockchain.info/tobtc?currency=EUR&value=1";
	public static final String BTC_FEE_PREDICTION_EARN_URL = "https://bitcoinfees.earn.com/api/v1/fees/recommended";
	public final static int BTC_FEE_MINUTES_TO_UPDATE = 30;
	public final static int BTC_FEE_HOURS_TO_UPDATE_HISTORIC = 2;
	public final static int BTC_RATE_HOURS_UPDATE = 1;
	public final static float BTC_USD_RATE_START = 0.00015412f;
	public final static float BTC_BRL_RATE_START = 0.00004153f;
	public final static float BTC_EUR_RATE_START = 0.00017632f;

	public final static int TRADING_MAX_RESULTS = 100;
	public final static String TRADING_DB_USERNAME = "canela";
	public final static String TRADING_DB_PASSWORD = "elterrordelbarrio";
	public final static String TRADING_URL = "objectdb://trimatek.org:6136/trading.odb";
	public final static String SERVER_NAME = "trimatek.org";
	
	public final static String ROUTE_TRADE_LOG = "/digidata/trade/log";

}
