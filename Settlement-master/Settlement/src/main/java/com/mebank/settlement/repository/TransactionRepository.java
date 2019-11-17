package com.mebank.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mebank.settlement.entity.Transaction;


/**
 *
 */
public interface TransactionRepository extends JpaRepository<Transaction, String>{
	

}
