package com.retail.reward.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.reward.model.TransactionModel;
import com.retail.reward.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	
	@Value("classpath:transactions.json")
	Resource transactionFile;
	
	@Override
	public List<TransactionModel> getAllTransactions() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<TransactionModel> transactionModels = mapper.readValue(transactionFile.getFile(), new TypeReference<List<TransactionModel>>(){});
		return transactionModels;
	}
	


}
