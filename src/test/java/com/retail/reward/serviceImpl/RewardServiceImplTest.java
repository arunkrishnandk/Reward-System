package com.retail.reward.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.retail.reward.dao.TransactionDao;
import com.retail.reward.model.RewardModel;
import com.retail.reward.model.TransactionModel;

@SpringBootTest
public class RewardServiceImplTest {
	
	@InjectMocks
	RewardServiceImpl rewardServiceImpl;
	
	@Mock
	TransactionDao transactionDaoImpl;
	
	@Test
	public void testGetRewardDetails() throws IOException {
		List<TransactionModel> transactions = new ArrayList<TransactionModel>();
		TransactionModel transaction = new TransactionModel();
		transaction.setAmount(BigDecimal.valueOf(70));
		transaction.setCustomerId(1L);
		transaction.setCustomerName("SAM");
		transaction.setTransactionDate("JAN-2023");
		transactions.add(transaction );
		TransactionModel transaction1 = new TransactionModel();
		transaction1.setAmount(BigDecimal.valueOf(120));
		transaction1.setCustomerId(1L);
		transaction1.setCustomerName("SAM");
		transaction1.setTransactionDate("DEC-2022");
		transactions.add(transaction1);
		TransactionModel transaction2 = new TransactionModel();
		transaction2.setAmount(BigDecimal.valueOf(140));
		transaction2.setCustomerId(2L);
		transaction2.setCustomerName("ADAM");
		transaction2.setTransactionDate("JAN-2023");
		transactions.add(transaction2);
		Mockito.when(transactionDaoImpl.getTransactionDataFromFile()).thenReturn(transactions );
		RewardModel result = rewardServiceImpl.getRewardDetails(1L);
		assertNotNull(result);
		assertEquals(110, result.getTotalRewards());
		assertEquals("SAM", result.getCustomerName());
	}
	
	@Test
	public void testGetRewardDetailsEmpty() throws IOException {
		List<TransactionModel> transactions = new ArrayList<TransactionModel>();
		TransactionModel transaction = new TransactionModel();
		transaction.setAmount(BigDecimal.valueOf(70));
		transaction.setCustomerId(1L);
		transaction.setCustomerName("SAM");
		transaction.setTransactionDate("JAN-2023");
		transactions.add(transaction );
		TransactionModel transaction1 = new TransactionModel();
		transaction1.setAmount(BigDecimal.valueOf(120));
		transaction1.setCustomerId(1L);
		transaction1.setCustomerName("SAM");
		transaction1.setTransactionDate("DEC-2022");
		transactions.add(transaction1);
		TransactionModel transaction2 = new TransactionModel();
		transaction2.setAmount(BigDecimal.valueOf(140));
		transaction2.setCustomerId(2L);
		transaction2.setCustomerName("ADAM");
		transaction2.setTransactionDate("JAN-2023");
		transactions.add(transaction2);
		Mockito.when(transactionDaoImpl.getTransactionDataFromFile()).thenReturn(transactions );
		RewardModel result = rewardServiceImpl.getRewardDetails(5L);
		assertNotNull(result);
		assertEquals(null, result.getTotalRewards());
		assertEquals(null, result.getCustomerName());
	}
	
	@Test
	public void testAllGetRewardDetails() throws IOException {
		List<TransactionModel> transactions = new ArrayList<TransactionModel>();
		TransactionModel transaction = new TransactionModel();
		transaction.setAmount(BigDecimal.valueOf(70));
		transaction.setCustomerId(1L);
		transaction.setCustomerName("SAM");
		transaction.setTransactionDate("JAN-2023");
		transactions.add(transaction );
		TransactionModel transaction1 = new TransactionModel();
		transaction1.setAmount(BigDecimal.valueOf(120));
		transaction1.setCustomerId(1L);
		transaction1.setCustomerName("SAM");
		transaction1.setTransactionDate("DEC-2022");
		transactions.add(transaction1);
		TransactionModel transaction2 = new TransactionModel();
		transaction2.setAmount(BigDecimal.valueOf(140));
		transaction2.setCustomerId(2L);
		transaction2.setCustomerName("ADAM");
		transaction2.setTransactionDate("JAN-2023");
		transactions.add(transaction2);
		Mockito.when(transactionDaoImpl.getTransactionDataFromFile()).thenReturn(transactions );
		List<RewardModel> result = rewardServiceImpl.getAllRewardDetails();
		assertNotNull(result);
		assertEquals(110, result.get(0).getTotalRewards());
		assertEquals("SAM", result.get(0).getCustomerName());
	}
	
	

}
