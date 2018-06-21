package org.trimatek.digideal.ui.utils;

import java.io.IOException;

import org.trimatek.digideal.ui.Config;
import org.trimatek.digideal.ui.model.Address;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.GeocodingResult;

public class Geocoder {

	public static Address geocode(String addressString) {
		Address address = null;
		try {
			GeoApiContext context = new GeoApiContext.Builder().apiKey(Config.GOOGLE_GEO_API_KEY).build();
			GeocodingResult[] results = GeocodingApi.geocode(context, addressString).await();
			if (results[0].addressComponents != null) {
				AddressComponent comps[] = results[0].addressComponents;
				address = Address.build(comps);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}

	public static void main(String[] args) throws IOException, ApiException, InterruptedException {
		String address = "amenabar 2020 buenos aires";
		geocode(address);
		/*
		 * String query =
		 * "https://maps.googleapis.com/maps/api/geocode/json?address=amenabar 2020 piso 1 dto B buenos Aires&key=AIzaSyAcLmRFJceRjP-Wkg8NQ2XOm-6cvML8A8E"
		 * ;
		 * 
		 */

	}
}
