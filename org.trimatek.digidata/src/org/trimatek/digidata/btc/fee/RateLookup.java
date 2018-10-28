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
	private BigDecimal dollar = new BigDecimal(Config.BTC_USD_RATE_START);
	private BigDecimal reais = new BigDecimal(Config.BTC_BRL_RATE_START);
	private BigDecimal euro = new BigDecimal(Config.BTC_EUR_RATE_START);

	private RateLookup() {
		ScheduledExecutorService exe = Executors.newScheduledThreadPool(3);
		exe.scheduleAtFixedRate(updateUsd, 0, Config.BTC_RATE_HOURS_UPDATE, TimeUnit.HOURS);
		exe.scheduleAtFixedRate(updateBrl, 0, Config.BTC_RATE_HOURS_UPDATE, TimeUnit.HOURS);
		exe.scheduleAtFixedRate(updateEur, 0, Config.BTC_RATE_HOURS_UPDATE, TimeUnit.HOURS);
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
				logger.log(Level.INFO, "New USD rate data received");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	};

	Runnable updateBrl = () -> {
		try {
			String result = Client.request(Config.BTC_BRL_RATE_BCINFO_URL);
			if (result != null) {
				reais = new BigDecimal(result);
				logger.log(Level.INFO, "New BRL rate data received");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	};
	
	Runnable updateEur = () -> {
		try {
			String result = Client.request(Config.BTC_EUR_RATE_BCINFO_URL);
			if (result != null) {
				euro = new BigDecimal(result);
				logger.log(Level.INFO, "New EUR rate data received");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	};

	public String getRates() {
		StringBuffer sb = new StringBuffer();
		sb.append("USD=" + dollar + "\n");
		sb.append("BRL=" + reais  + "\n");
		sb.append("EUR=" + euro  + "\n");
		return sb.toString();
	}

}
