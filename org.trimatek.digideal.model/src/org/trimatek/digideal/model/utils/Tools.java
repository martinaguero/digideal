package org.trimatek.digideal.model.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		return System.getProperty("os.name").toLowerCase().indexOf("win") > 1 ? false : true;
	}

}
