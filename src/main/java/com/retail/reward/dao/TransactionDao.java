package com.retail.reward.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.retail.reward.model.TransactionModel;

@Repository
public interface TransactionDao {
	
	List<TransactionModel> getTransactionDataFromFile() throws IOException;

}
