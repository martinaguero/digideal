package org.trimatek.digideal.bitcoin.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.trimatek.digideal.bitcoin.entities.Context;
import org.trimatek.digideal.model.Transaction;
import org.trimatek.digideal.model.utils.Config;

public class Calc {

	private final static Logger logger = Logger.getLogger(Calc.class.getName());

	public static int calcBytes(int inputs, int outputs) {
		return ((inputs * 148) + (outputs * 34) + 10);
	}

	public static BigDecimal satoshiToBtc(Long sts) {
		return new BigDecimal(sts).divide(new BigDecimal(Context.COIN_VALUE), Context.DEFAULT_SCALE,
				RoundingMode.CEILING);
	}

	public static int calcFee(int bytes) {
		String result = getFeePrediction(Config.getValue("BTC_FEE_URL"));
		if (result == null) {
			result = getFeePrediction(Config.getValue("BTC_FEE_HIST_URL"));
		}
		return result != null ? bytes * Integer.valueOf(result) : Context.STS_PER_BYTE;
	}

	public static BigDecimal addTransactions(Set<Transaction> unspents) {
		BigDecimal result = new BigDecimal(0);
		for (Transaction t : unspents) {
			result = result.add(t.getValue());
		}
		return result;
	}

	private static String getFeePrediction(String URL) {
		StringBuffer response = new StringBuffer();
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(URL).openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			if (con.getResponseCode() == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return response.toString();
	}

}
