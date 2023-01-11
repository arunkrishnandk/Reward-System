package com.retail.reward.service;


import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.retail.reward.model.TransactionModel;


@Service
public interface TransactionService {

	List<TransactionModel> getAllTransactions() throws IOException;


}
