package com.mebank.settlement.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mebank.dto.TransactionDTO;
import com.mebank.settlement.entity.Transaction;
import com.mebank.settlement.exception.TransactionAlreadyExists;
import com.mebank.settlement.service.TransactionService;

/**
 * @author 
 *
 */
@RestController
@RequestMapping(path = "/api/v1/settlement")
public class SettlementController {

	@Autowired
	private TransactionService transactionService;

	@GetMapping
	public ResponseEntity<?> loadDataFromCSV(HttpServletRequest request, HttpServletResponse response) throws TransactionAlreadyExists {
		ResponseEntity<?> responseEntity;
		List<Transaction> transactionList = transactionService.loadObjectList( "settlement.csv");
		transactionService.saveAll(transactionList);
		responseEntity = new ResponseEntity<>(transactionList,HttpStatus.CREATED);
		return responseEntity;
	}
	
	@PostMapping(path="/transactions")
	public ResponseEntity<?> retrieveBalanceForPeriod(@RequestBody final TransactionDTO dto,
			HttpServletRequest request, HttpServletResponse response) throws ParseException{
		ResponseEntity<?> responseEntity;
		Map<String, Object> result = transactionService.getRelativeAccountBalace(dto.getAccountId(), dto.getFromDate(), dto.getToDate());
		responseEntity = new ResponseEntity<>(result,HttpStatus.OK);
		return responseEntity;
		
	}
	
	
}
