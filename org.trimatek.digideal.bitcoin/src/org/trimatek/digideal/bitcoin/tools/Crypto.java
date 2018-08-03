package org.trimatek.digideal.bitcoin.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Crypto {

	public static void main(String args[])
			throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {

		// String pub =
		// "0250863ad64a87ae8a2fe83c1af1a8403cb53f53e486d8511dad8a04887e5b2352";
		String pub = "02809f0aa04ba008462915afa2635dd28f6b8ed37e400984440404a641bfaeb4ae";

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(BinAscii.unhexlify(pub));

		System.out.println(bytesToHex(encodedhash));

		Security.addProvider(new BouncyCastleProvider());

		MessageDigest rmd = MessageDigest.getInstance("RipeMD160", "BC");
		byte[] r1 = rmd.digest(encodedhash);

		String paso3 = bytesToHex(r1);
		System.out.println(paso3);

		// String paso4 = "00" + paso3;
		String paso4 = "6f" + paso3;

		System.out.println(paso4);

		byte[] sha = digest.digest(BinAscii.unhexlify(paso4));

		String paso5 = bytesToHex(sha);

		System.out.println(paso5);

		byte[] sha2 = digest.digest(BinAscii.unhexlify(paso5));

		String paso6 = bytesToHex(sha2);

		System.out.println(paso6);

		byte[] checksum = subByte(sha2, 4);
		System.out.println(bytesToHex(checksum));

		byte[] address = append(BinAscii.unhexlify(paso4), checksum);
		System.out.println(Base58.encode(address));

	}

	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	static byte[] subByte(byte[] source, int length) {
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++) {
			result[i] = source[i];
		}
		return result;
	}

	static byte[] append(byte[] a, byte[] b) {
		int size = a.length + b.length;
		byte[] result = new byte[size];
		for (int i = 0; i < size; i++) {
			result[i] = i < a.length ? a[i] : b[i - a.length];
		}
		return result;
	}

}
