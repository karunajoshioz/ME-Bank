package com.mebank.settlement.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mebank.settlement.entity.Transaction;
import com.mebank.settlement.exception.TransactionAlreadyExists;
import com.mebank.settlement.repository.TransactionRepository;

public class TransactionserviceImplTests {

	@Mock
	private transient TransactionRepository repo;

	private transient Transaction transaction;

	@InjectMocks
	private TransactionServiceImpl service;

	transient Optional<Transaction> options;

	@Before
	public void setupMock() throws ParseException {
		MockitoAnnotations.initMocks(this);
		List<Transaction> transactions = new ArrayList<>();

		String date1 = "20/10/2018 12:47:55";
		String date3 = "21/10/2018 12:47:55";

		transaction = new Transaction("TX10001", "ACC334455", "ACC998877", date1, 10.5, "PAYMENT", null);
		transactions.add(transaction);
		transaction = new Transaction("TX10002", "ACC334455", "ACC998877", date1, 10.5, "PAYMENT", null);
		transactions.add(transaction);
		transaction = new Transaction("TX10003", "ACC334455", "ACC998877", date3, 10.5, "PAYMENT", "TX10006");
		transactions.add(transaction);

	}

	@Test
	public void testMockCreation() {
		assertNotNull("JPA repo creation failed", transaction);
	}

	@Test
	public void testLoadObjectList() throws ParseException {
		List<Transaction> transactionList = service.loadObjectList("settlement.csv");
		assertNotNull("loading Transaction from CSV failed", transactionList);
		transactionList.forEach((trans) -> {
			assertNotNull(trans.getTransactionId());
			assertNotNull(trans.getFromAccountId());
			assertNotNull(trans.getToAccountId());
			assertNotNull(trans.getTransactionType());
			assertNotNull(trans.getCreateAt());
			assertNotNull(trans.getRelatedTransaction());
			assertNotNull(trans.getAmount());
		});
		assert (transactionList.size() == 5);
	}

	@Test
	public void testSaveAllSucess() throws TransactionAlreadyExists, ParseException {
		List<Transaction> transactionList = generateTransactionList();
		when(repo.saveAll(transactionList)).thenReturn(transactionList);
		final boolean flag = service.saveAll(transactionList);
		assertTrue("Saving Transaction success", flag);
		verify(repo, times(1)).saveAll(transactionList);
		verify(repo, times(1)).findAll();

	}

	@Test(expected = TransactionAlreadyExists.class)
	public void testSaveAllFailed() throws TransactionAlreadyExists, ParseException {
		List<Transaction> transactionList = generateTransactionList();
		when(repo.findAll()).thenReturn(transactionList);
		when(repo.saveAll(transactionList)).thenReturn(transactionList);
		final boolean flag = service.saveAll(transactionList);
		assertFalse("Saving Transactions failed", flag);
	}

	@Test
	public void testGetRelativeAccountBalance() throws ParseException, TransactionAlreadyExists {

		String date1 = "20/10/2018 12:47:55";
		String date5 = "21/10/2018 12:47:55";
		List<Transaction> transactionList = generateTransactionList();
		when(repo.saveAll(transactionList)).thenReturn(transactionList);
		when(repo.findAll()).thenReturn(transactionList);
		Map<String, Object> resultMap = service.getRelativeAccountBalace("ACC334455", date1, date5);
		Map<String, Object> expectedResult = new HashMap<>();
		expectedResult.put("Relative Amount", -32.25);
		expectedResult.put("Number of Transactions Included", 2);
		assertEquals(expectedResult, resultMap);

	}

	@Test
	public void testGetRelativeAccountBalanceWithAcoountIdAsBeneficiary()
			throws ParseException, TransactionAlreadyExists {

		String date1 = "20/10/2018 12:47:55";
		String date5 = "21/10/2018 12:47:55";

		List<Transaction> transactionList = generateTransactionListWithAccountNumberAsBeneficiary();

		when(repo.saveAll(transactionList)).thenReturn(transactionList);
		when(repo.findAll()).thenReturn(transactionList);
		Map<String, Object> resultMap = service.getRelativeAccountBalace("ACC334455", date1, date5);
		Map<String, Object> expectedResult = new HashMap<>();
		expectedResult.put("Relative Amount", -27.25);
		expectedResult.put("Number of Transactions Included", 3);
		assertEquals(expectedResult, resultMap);
	}

	public List<Transaction> generateTransactionList() throws ParseException {
		List<Transaction> transactions = new ArrayList<>();

		String date1 = "20/10/2018 12:47:55";
		String date2 = "20/10/2018 17:33:43";
		String date3 = "20/10/2018 18:00:00";
		String date4 = "20/10/2018 19:45:00";
		String date5 = "21/10/2018 09:30:00";

		Transaction t = new Transaction("TX10001", "ACC334455", "ACC778899", date1, 25.00, "PAYMENT", null);
		transactions.add(t);
		t = new Transaction("TX10002", "ACC334455", "ACC998877", date2, 10.50, "PAYMENT", null);
		transactions.add(t);
		t = new Transaction("TX10003", "ACC998877", "ACC778899", date3, 5.00, "PAYMENT", null);
		transactions.add(t);
		t = new Transaction("TX10004", "ACC334455", "ACC998877", date3, 10.50, "REVERSAL", "TX10002");
		transactions.add(t);
		t = new Transaction("TX10005", "ACC334455", "ACC778899", date5, 7.25, "PAYMENT", null);
		transactions.add(t);

		return transactions;
	}

	public List<Transaction> generateTransactionListWithAccountNumberAsBeneficiary() throws ParseException {
		List<Transaction> transactions = new ArrayList<>();

		String date1 = "20/10/2018 12:47:55";
		String date2 = "20/10/2018 17:33:43";
		String date3 = "20/10/2018 18:00:00";
		String date4 = "20/10/2018 19:45:00";
		String date5 = "21/10/2018 09:30:00";

		Transaction t = new Transaction("TX10001", "ACC334455", "ACC778899", date1, 25.00, "PAYMENT", null);
		transactions.add(t);
		t = new Transaction("TX10002", "ACC334455", "ACC998877", date2, 10.50, "PAYMENT", null);
		transactions.add(t);
		t = new Transaction("TX10003", "ACC998877", "ACC334455", date3, 5.00, "PAYMENT", null);
		transactions.add(t);
		t = new Transaction("TX10004", "ACC334455", "ACC998877", date3, 10.50, "REVERSAL", "TX10002");
		transactions.add(t);
		t = new Transaction("TX10005", "ACC334455", "ACC778899", date5, 7.25, "PAYMENT", null);
		transactions.add(t);

		return transactions;

	}
}
