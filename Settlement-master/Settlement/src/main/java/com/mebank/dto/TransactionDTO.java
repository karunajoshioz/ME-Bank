package com.mebank.dto;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class TransactionDTO {
	
    private String accountId;
	
    
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd hh:mm:ss")
	private String fromDate;
	
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd hh:mm:ss")
	private String toDate;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	
	

}
