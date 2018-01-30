package org.trimatek.digideal.bitcoin.entities;

public class Transaction {

	private String signedBy;
	private String raw;
	
	public Transaction(String signedBy, String raw){
		this.signedBy = signedBy;
		this.raw = raw;
	}

	public String getSignedBy() {
		return signedBy;
	}

	public void setSignedBy(String signedBy) {
		this.signedBy = signedBy;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

}
