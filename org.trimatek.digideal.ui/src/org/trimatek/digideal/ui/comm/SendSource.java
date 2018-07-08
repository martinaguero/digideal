package org.trimatek.digideal.ui.comm;

import java.net.HttpURLConnection;
import java.net.URL;

import org.trimatek.digideal.ui.Config;
import org.trimatek.digideal.ui.model.Source;
import org.trimatek.digideal.ui.utils.SourceBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SendSource {

	private Source source;

	Runnable sendSource = () -> {
		try {
			URL url = new URL(Config.DIGIDEAL_URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.addRequestProperty("Content-Type", "application/" + "POST");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String sourceSerial = gson.toJson(SourceBuilder.formatToGo(source));
			System.out.println("Sending:\n");
			System.out.println(sourceSerial);
			con.setRequestProperty("Content-Length", Integer.toString(sourceSerial.length()));
			con.getOutputStream().write(sourceSerial.getBytes("UTF8"));
			int responseCode = con.getResponseCode();
			System.out.println("Response Code : " + responseCode);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	};

	public static void exec(Source source) {
		SendSource ss = new SendSource();
		ss.source = source;
		new Thread(ss.sendSource).start();
	}

}
