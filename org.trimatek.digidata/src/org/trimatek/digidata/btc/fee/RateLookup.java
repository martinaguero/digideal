package org.trimatek.digidata.btc.fee;

import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.trimatek.digidata.Config;
import org.trimatek.digidata.rest.Client;

public class RateLookup {

	private final static Logger logger = Logger.getLogger(RateLookup.class.getName());
	private static RateLookup INSTANCE;
	private BigDecimal dollar = new BigDecimal(Config.BTC_USD_RATE_LAST);

	private RateLookup() {
		ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
		exe.scheduleAtFixedRate(updateUsd, 0, Config.BTC_RATE_HOURS_UPDATE, TimeUnit.HOURS);
	}

	public static RateLookup getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RateLookup();
		}
		return INSTANCE;
	}

	Runnable updateUsd = () -> {
		try {
			String result = Client.request(Config.BTC_USD_RATE_BCINFO_URL);
			if (result != null) {
				dollar = new BigDecimal(result);
				logger.log(Level.INFO, "New dollar rate data received");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	};

	public String getUsdRate() {
		return dollar.toString();
	}

}
