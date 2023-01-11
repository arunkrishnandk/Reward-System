package com.retail.reward.daoImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.reward.dao.TransactionDao;
import com.retail.reward.model.TransactionModel;

@Repository
public class TransactionDaoImpl implements TransactionDao {
	
	@Value("classpath:transactions.json")
	Resource transactionFile;

	@Override
	public List<TransactionModel> getTransactionDataFromFile() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<TransactionModel> transactionModels = mapper.readValue(transactionFile.getFile(), new TypeReference<List<TransactionModel>>(){});
		return transactionModels;
	}
}
