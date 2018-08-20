package org.trimatek.digidata.fee;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeeLookup {

	private final static Logger logger = Logger.getLogger(FeeLookup.class.getName());
	private final static String EARN_URL = "https://bitcoinfees.earn.com/api/v1/fees/recommended";
	private final static int MINUTES_TO_UPDATE = 30;
	private static FeeLookup INSTANCE;
	private String lastFast, lastMid, lastSlow;
	private Instant updateTime;

	private FeeLookup() {
	}

	public static FeeLookup getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FeeLookup();
		}
		return INSTANCE;
	}

	private String updatePrediction() {
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
				logger.log(Level.INFO, "New fee data received");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Fee data could not be received");
		}
		return response.toString();
	}

	private boolean requiresUpdate() {
		return updateTime == null || ChronoUnit.MINUTES.between(Instant.now(), updateTime) > MINUTES_TO_UPDATE ? true
				: false;
	}

	public String getFee(FEES option) {
		if (requiresUpdate()) {
			updateFees(updatePrediction());
		}
		return option.compareTo(FEES.FAST) == 0 ? lastFast : option.compareTo(FEES.SLOW) == 0 ? lastSlow : lastMid;
	}

	private void updateFees(String predictions) {
		predictions = predictions.replace("{", "");
		predictions = predictions.replace("}", "");
		predictions = predictions.replace("\"", "");
		String data[] = predictions.split(",");
		for (String s : data) {
			if (s.contains("fastestFee")) {
				lastFast = s.substring(s.indexOf(":") + 1);
			} else if (s.contains("halfHourFee")) {
				lastMid = s.substring(s.indexOf(":") + 1);
			} else {
				lastSlow = s.substring(s.indexOf(":") + 1);
			}
		}
	}

	public enum FEES {
		FAST, MID, SLOW;
	}

}
