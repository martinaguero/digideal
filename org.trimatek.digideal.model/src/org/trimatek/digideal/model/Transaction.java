package org.trimatek.digideal.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	private String txId;
	private String signedBy;
	private String raw;
	private String outputScript;
	private int vout;
	private BigDecimal value;
	
	public Transaction(String txId) {
		this.txId = txId;
	}

	public Transaction(String signedBy, String raw) {
		this.signedBy = signedBy;
		this.raw = raw;
	}

	public long getId() {
		return id;
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

	public String getTxId() {
		return txId;
	}

	public void setTxId(String txId) {
		this.txId = txId;
	}

	public String getOutputScript() {
		return outputScript;
	}

	public void setOutputScript(String outputScript) {
		this.outputScript = outputScript;
	}

	public int getVout() {
		return vout;
	}

	public void setVout(int vout) {
		this.vout = vout;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	

}
