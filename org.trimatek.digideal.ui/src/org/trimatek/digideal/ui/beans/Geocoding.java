package org.trimatek.digideal.ui.beans;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.GeocodingResult;

public class Geocoding {

	public static void main(String[] args) throws IOException, ApiException, InterruptedException {
		String address = "amenabar 2020 buenos aires argentina";
		test(address);
		/*
		String query = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address
				+ "&key=AIzaSyAcLmRFJceRjP-Wkg8NQ2XOm-6cvML8A8E";
		
		System.out.println(query);

		URL obj = new URL(query);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		//con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		//if (con.getResponseCode() == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			Gson gson = new Gson();
			GoogleGeoCodeResponse result = gson.fromJson(response.toString(), GoogleGeoCodeResponse.class);

			System.out.println(result.status);

			// https://developers.google.com/maps/documentation/geocoding/intro

	//	}
	 */ 
	
	}
	
	
	
	public static void test(String address) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyAcLmRFJceRjP-Wkg8NQ2XOm-6cvML8A8E")
			    .build();
			GeocodingResult[] results =  GeocodingApi.geocode(context,
			    address).await();
			AddressComponent comps [] = results[0].addressComponents;

			/*Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(results[0].addressComponents));*/
	}
}
