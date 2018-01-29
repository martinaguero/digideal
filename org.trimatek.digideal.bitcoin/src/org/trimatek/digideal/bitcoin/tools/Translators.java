package org.trimatek.digideal.bitcoin.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class Translators {
	
	public static String toString(InputStream stream) throws IOException {
		return new BufferedReader(new InputStreamReader(stream)).lines().parallel().collect(Collectors.joining("\n"));
	}

}
