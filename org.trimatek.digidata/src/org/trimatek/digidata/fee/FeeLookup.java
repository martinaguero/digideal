package org.trimatek.digidata.fee;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeeLookup {

	private final static Logger logger = Logger.getLogger(FeeLookup.class.getName());
	private final static String EARN_URL = "https://bitcoinfees.earn.com/api/v1/fees/recommended";
	private final static int MINUTES_TO_UPDATE = 30;
	private final static int HOURS_TO_UPDATE_HISTORIC = 2;
	private static FeeLookup INSTANCE;
	private String lastFast, lastMid, lastSlow;
	private BigDecimal histoFast = new BigDecimal(0);
	private BigDecimal histoMid = new BigDecimal(0);
	private BigDecimal histoSlow = new BigDecimal(0);
	private int count = 0;
	private Instant updateTime;
	private StringBuffer errors = new StringBuffer();

	private FeeLookup() {
		ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);
		exe.scheduleAtFixedRate(updatePrediction, 0, HOURS_TO_UPDATE_HISTORIC, TimeUnit.HOURS);
	}

	public static FeeLookup getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FeeLookup();
		}
		return INSTANCE;
	}

	Runnable updatePrediction = () -> {
		StringBuffer response = new StringBuffer();
		try {
			URL url = new URL(EARN_URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			if (con.getResponseCode() == 200) {
				updateTime = Instant.now();
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				recordPredictions(response.toString());
				logger.log(Level.INFO, "New fee data received");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			errors.append(e.getMessage());
		}
	};

	private void recordPredictions(String predictions) {
		predictions = predictions.replace("{", "");
		predictions = predictions.replace("}", "");
		predictions = predictions.replace("\"", "");
		String data[] = predictions.split(",");
		if (data.length > 0) {
			count++;
		}
		for (String s : data) {
			if (s.contains("fastestFee")) {
				lastFast = s.substring(s.indexOf(":") + 1);
				histoFast = (histoFast.add(new BigDecimal(lastFast)));
			} else if (s.contains("halfHourFee")) {
				lastMid = s.substring(s.indexOf(":") + 1);
				histoMid = histoMid.add(new BigDecimal(lastMid));
			} else {
				lastSlow = s.substring(s.indexOf(":") + 1);
				histoSlow = histoSlow.add(new BigDecimal(lastSlow));
			}
		}
	}

	private boolean requiresUpdate() {
		return updateTime == null || ChronoUnit.MINUTES.between(Instant.now(), updateTime) > MINUTES_TO_UPDATE ? true
				: false;
	}

	public String getFee(FEES option) {
		if (requiresUpdate()) {
			try {
				Thread t = new Thread(updatePrediction);
				t.start();
				t.join();
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
		}
		return option.compareTo(FEES.FAST) == 0 ? lastFast : option.compareTo(FEES.SLOW) == 0 ? lastSlow : lastMid;
	}

	public String getHisto(FEES option) {
		return option.compareTo(FEES.FAST) == 0 ? histoFast.divide(new BigDecimal(count)).intValue() + ""
				: option.compareTo(FEES.SLOW) == 0 ? histoSlow.divide(new BigDecimal(count)).intValue() + ""
						: histoMid.divide(new BigDecimal(count)).intValue() + "";
	}

	public enum FEES {
		FAST, MID, SLOW;
	}
	
	public String getErrors() {
		return errors.toString();
	}

}
