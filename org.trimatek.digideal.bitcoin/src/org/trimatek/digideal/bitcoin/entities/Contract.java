package org.trimatek.digideal.bitcoin.entities;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Contract {

	private String doc;
	private byte[] metadata;
	private String multisigAddress;
	private String redeemScript;
	private String unspentTxId;
	private String unspentRaw;
	private String unspentOutputScript;
	private int unspentVout;

	public Contract() {
	}

	public Contract(String contract, String propertiesFilePath) throws FileNotFoundException, IOException {
		doc = contract;
		Path source = Paths.get(propertiesFilePath);
		metadata = Files.readAllBytes(source);
	}

	public String getValue(String key) throws IOException {
		Properties prop = new Properties();
		prop.load(new ByteArrayInputStream(metadata));
		return prop != null ? prop.getProperty(key) : null;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public byte[] getMetadata() {
		return metadata;
	}

	public void setMetadata(byte[] metadata) {
		this.metadata = metadata;
	}

	public String getMultisigAddress() {
		return multisigAddress;
	}

	public void setMultisigAddress(String multisigAddress) {
		this.multisigAddress = multisigAddress;
	}

	public String getRedeemScript() {
		return redeemScript;
	}

	public void setRedeemScript(String redeemScript) {
		this.redeemScript = redeemScript;
	}

	public String getUnspentTxId() {
		return unspentTxId;
	}

	public void setUnspentTxId(String unspentTxId) {
		this.unspentTxId = unspentTxId;
	}

	public String getUnspentOutputScript() {
		return unspentOutputScript;
	}

	public void setUnspentOutputScript(String unspentOutputScript) {
		this.unspentOutputScript = unspentOutputScript;
	}

	public int getUnspentVout() {
		return unspentVout;
	}

	public void setUnspentVout(int unspentVout) {
		this.unspentVout = unspentVout;
	}

	public String getUnspentRaw() {
		return unspentRaw;
	}

	public void setUnspentRaw(String unspentRaw) {
		this.unspentRaw = unspentRaw;
	}

}
