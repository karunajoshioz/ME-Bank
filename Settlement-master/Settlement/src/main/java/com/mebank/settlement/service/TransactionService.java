package com.mebank.settlement.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mebank.settlement.entity.Transaction;
import com.mebank.settlement.exception.TransactionAlreadyExists;


public interface TransactionService {
	public  List<Transaction> loadObjectList(String fileName);
	
	public boolean saveAll(List<Transaction> transactionList) throws TransactionAlreadyExists;
		
	public  Map<String, Object> getRelativeAccountBalace(String accountId, String fromDate, String toDate) throws ParseException;
	
	//public List<Transaction> removeReversedTransaction(List<Transaction> TransactionList);
	
	

}
