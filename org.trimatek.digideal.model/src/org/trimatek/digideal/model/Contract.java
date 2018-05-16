package org.trimatek.digideal.model;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Properties;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.trimatek.digideal.model.utils.Tools;

@Entity
public class Contract implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	@Embedded
	private Source source;
	private byte[] instructions;
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
	private String spentTxId;
	//
	private String statusName;
	//
	@Embedded
	private Receipt receipt;

	public Contract() {
	}

	public Contract(Source source) {
		this.source = source;
	}

	public Contract(Source source, String propertiesFilePath) throws FileNotFoundException, IOException {
		this.source = source;
		instructions = Tools.readBytes(propertiesFilePath);
	}

	public String getValue(String key) throws IOException {
		Properties prop = new Properties();
		prop.load(new ByteArrayInputStream(instructions));
		return prop != null ? prop.getProperty(key) : null;
	}

	public long getId() {
		return id;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public byte[] getInstructions() {
		return instructions;
	}

	public void setInstructions(byte[] instructions) {
		this.instructions = instructions;
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

	public void setInstructions(String propertiesFilePath) throws IOException {
		instructions = Tools.readBytes(propertiesFilePath);
	}
}
