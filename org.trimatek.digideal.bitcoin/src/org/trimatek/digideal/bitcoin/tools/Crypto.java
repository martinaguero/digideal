package org.trimatek.digideal.bitcoin.tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Crypto {

	private static Crypto INSTANCE;
	private MessageDigest sha256;
	private MessageDigest ripeMd160;
	private final static Logger logger = Logger.getLogger(Crypto.class.getName());

	private Crypto() {
		Security.addProvider(new BouncyCastleProvider());
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
			ripeMd160 = MessageDigest.getInstance("RipeMD160", "BC");
		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} catch (NoSuchProviderException e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	public static Crypto getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Crypto();
		}
		return INSTANCE;
	}

	public String toAddress(String publicKey, boolean mainnet) {
		byte[] sha1 = sha256.digest(BinAscii.unhexlify(publicKey));
		byte[] ripe = ripeMd160.digest(sha1);
		String hexa = bytesToHex(ripe);
		hexa = mainnet ? "00" + hexa : "6f" + hexa;
		byte[] sha3 = sha256.digest(sha256.digest(BinAscii.unhexlify(hexa)));
		byte[] checksum = subByte(sha3, 4);
		byte[] address = append(BinAscii.unhexlify(hexa), checksum);
		return Base58.encode(address);
	}

	public static void main(String args[])
			throws NoSuchAlgorithmException, UnsupportedEncodingException, NoSuchProviderException {

		System.out.println(Crypto.getInstance()
				.toAddress("02809f0aa04ba008462915afa2635dd28f6b8ed37e400984440404a641bfaeb4ae", false));
		// Resultado: mya3L6VEpN2rWR7x4PAbbf9KBvTsYRXUh4

		System.out.println(Crypto.getInstance()
				.toAddress("0250863ad64a87ae8a2fe83c1af1a8403cb53f53e486d8511dad8a04887e5b2352", true));
		// Resultado: 1PMycacnJaSqwwJqjawXBErnLsZ7RkXUAs

		System.out.println(Crypto.getInstance()
				.toAddress("024aa7c2c05e5feca1f70c18b379d3db65339e164c9d9233077cbb3080ecd44b2d", false));
		// Resultado: 1PMycacnJaSqwwJqjawXBErnLsZ7RkXUAs

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
