package org.trimatek.digideal.comm.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
	
	private final static Logger logger = Logger.getLogger(Client.class.getName());

	public static String get(String URL) {
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
