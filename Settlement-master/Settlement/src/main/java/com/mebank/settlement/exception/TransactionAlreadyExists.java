package com.mebank.settlement.exception;

public class TransactionAlreadyExists extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionAlreadyExists(String message) {
		super(message);
	}
}
