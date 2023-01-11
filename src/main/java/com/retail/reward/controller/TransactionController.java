package com.retail.reward.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.reward.model.TransactionModel;
import com.retail.reward.service.TransactionService;

@RestController
public class TransactionController {
	
	
	@Autowired
	TransactionService transactionServiceImpl;
	
	@GetMapping(value = "/transaction")
	public ResponseEntity<List<TransactionModel>> createTransaction() throws IOException {
		return ResponseEntity.ok(transactionServiceImpl.getAllTransactions());

	}

}
