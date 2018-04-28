package org.trimatek.digideal.model;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Contract implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	private String doc;
	private byte[] metadata;
	private String receiptCode;
	private BigDecimal sts;
	private int requiredSignatures;
	//
	private String multisigAddress;
	private String redeemScript;
	//
	private String unspentTxId;
	private String unspentRaw;
	private String unspentOutputScript;
	private int unspentVout;
	//
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Transaction> payTransactions;
	private String agentPrivateKey;
	private String spentTxId;
	//
	private String statusName;
	//
	@Embedded
	private Receipt receipt;

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

	public long getId() {
		return id;
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

	public BigDecimal getSts() {
		return sts;
	}

	public void setSts(BigDecimal sts) {
		this.sts = sts;
	}

	public void addPayTransaction(Transaction transaction) {
		if (payTransactions == null) {
			payTransactions = new LinkedHashSet<Transaction>();
		}
		payTransactions.add(transaction);
	}

	public Set<Transaction> getPayTransactions() {
		return payTransactions;
	}

	public Transaction getLastPayTransaction() {
		return (Transaction) getPayTransactions().toArray()[getPayTransactions().size() - 1];
	}

	public int getRequiredSignatures() {
		return requiredSignatures;
	}

	public void setRequiredSignatures(int requiredSignatures) {
		this.requiredSignatures = requiredSignatures;
	}

	public int countPayTransactions() {
		return getPayTransactions().size();
	}

	public String getReceiptCode() {
		return receiptCode;
	}

	public void setReceiptCode(String receiptCode) {
		this.receiptCode = receiptCode;
	}

	public String getAgentPrivateKey() {
		return agentPrivateKey;
	}

	public void setAgentPrivateKey(String agentPrivateKey) {
		this.agentPrivateKey = agentPrivateKey;
	}

	public String getSpentTxId() {
		return spentTxId;
	}

	public void setSpentTxId(String spentTxId) {
		this.spentTxId = spentTxId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

}
