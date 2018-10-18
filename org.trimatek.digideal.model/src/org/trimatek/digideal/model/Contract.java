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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.trimatek.digideal.model.utils.Tools;

@Entity
@NamedQueries({
		@NamedQuery(name = "loadUndoneContracts", query = "SELECT c FROM Contract c WHERE c.statusName != 'Done' AND c.running = FALSE ORDER BY c.id"),
		@NamedQuery(name = "setRunningFalse", query = "UPDATE Contract c SET c.running = FALSE WHERE c.running = TRUE") })
public class Contract implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	@Embedded
	private Source source;
	private byte[] instructions;
	private String receiptCode;
	private BigDecimal btc;
	private int requiredSignatures;
	//
	private String address;
	@Transient
	private String privateKey;
	private String multisigAddress;
	private String redeemScript;
	//
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
	private Set<Transaction> unspentTransactions;
	//
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Transaction> payTransactions;
	private String spentTxId;
	//
	private String statusName;
	private Boolean running;
	private String comments;
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

	public BigDecimal getBtc() {
		return btc;
	}

	public void setBtc(BigDecimal btc) {
		this.btc = btc;
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

	public Boolean getRunning() {
		return running;
	}

	public void setRunning(Boolean running) {
		this.running = running;
	}

	public void addUnspentTransaction(Transaction transaction) {
		if (unspentTransactions == null) {
			unspentTransactions = new LinkedHashSet<Transaction>();
		}
		unspentTransactions.add(transaction);
	}

	public Set<Transaction> getUnspentTransactions() {
		return unspentTransactions;
	}

	public Transaction getLastUnspentTransaction() {
		return (Transaction) getUnspentTransactions().toArray()[getUnspentTransactions().size() - 1];
	}

	public Transaction removeLastUnspentTransaction() {
		Transaction t = getLastUnspentTransaction();
		unspentTransactions.remove(t);
		return t;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
