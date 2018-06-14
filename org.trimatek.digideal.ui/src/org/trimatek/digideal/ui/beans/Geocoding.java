package org.trimatek.digideal.ui.beans;

import java.io.IOException;

import org.trimatek.digideal.ui.model.Address;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.GeocodingResult;

public class Geocoding {

	public static void main(String[] args) throws IOException, ApiException, InterruptedException {
		String address = "amenabar 2020 buenos aire";
		test(address);
		/*
		 * String query = "https://maps.googleapis.com/maps/api/geocode/json?address=amenabar 2020 piso 1 dto B buenos Aires&key=AIzaSyAcLmRFJceRjP-Wkg8NQ2XOm-6cvML8A8E";
		 * 
		*/

	}

	public static void test(String address) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyAcLmRFJceRjP-Wkg8NQ2XOm-6cvML8A8E").build();
		GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
		AddressComponent comps[] = results[0].addressComponents;
		Address a = null;

		try {
			a = Address.build(comps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(a);
		
	}
}
