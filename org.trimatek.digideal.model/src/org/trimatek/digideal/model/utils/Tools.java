package org.trimatek.digideal.model.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import org.trimatek.digideal.model.Transaction;

public class Tools {

	public static byte[] readBytes(String path) throws IOException {
		Path source = Paths.get(path);
		return Files.readAllBytes(source);
	}

	public static boolean contains(String txid, Set<Transaction> transactions) {
		if (transactions != null) {
			for (Transaction tx : transactions) {
				if (tx.getTxId().equals(txid)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isUxHost() {
		return System.getProperty("os.name").toLowerCase().contains("win") ? false : true;
	}
	
	public static byte[] toByteArray(Properties props) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		props.store(output, null);
		return output.toByteArray();
	}
	
	public static String toString(Properties p) {
		StringBuilder sb = new StringBuilder();
		Enumeration keys = p.keys();
		while (keys.hasMoreElements()) {
		    String key = (String)keys.nextElement();
		    String value = (String)p.get(key);
		    sb.append(key + " = " + value + System.lineSeparator());
		}
		return sb.toString();
	} 

}
