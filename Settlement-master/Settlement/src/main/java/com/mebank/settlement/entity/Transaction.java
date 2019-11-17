package com.mebank.settlement.entity;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
@Table(name = "settlement_transaction")
@Entity
public class Transaction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "transaction_id")
	@JsonProperty
	private String transactionId;
	
	@Column(name = "from_account_id")
	@JsonProperty
	private String fromAccountId;
	
	@Column(name = "to_account_id")
	@JsonProperty
	private String toAccountId;
	
	@Column(name = "create_at")
	//@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	//@JsonProperty	//@JsonProperty(value="createAt")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/mm/yyyy hh:mm:ss")
	private String createAt;
	
	@Column(name = "amount")
	@JsonProperty
	private Double amount;
	
	@Column(name = "transaction_type")
	@JsonProperty
	private String transactionType;
	
	@Column(name = "related_transaction")
	@JsonProperty
	private String relatedTransaction;
	
	public Transaction() {
		
	}
	
	public Transaction(String transactionId, String fromAccountId, String toAccountId, String createAt, Double amount, String transactionType, String relatedTransaction) {
		this.transactionId = transactionId;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.createAt = createAt;
		this.amount = amount;
		this.transactionType = transactionType;
		this.relatedTransaction = relatedTransaction;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getRelatedTransaction() {
		return relatedTransaction;
	}

	public void setRelatedTransaction(String relatedTransaction) {
		this.relatedTransaction = relatedTransaction;
	}
	
	

}
