package com.retail.reward.serviceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.reward.constants.Constants;
import com.retail.reward.model.RewardModel;
import com.retail.reward.model.TransactionModel;
import com.retail.reward.service.RewardService;

@Service
public class RewardServiceImpl implements RewardService {
	
	@Value("classpath:transactions.json")
	Resource transactionFile;


	@Override
	public RewardModel getRewardDetails(@NotNull Long customerId) throws IOException {
		List<TransactionModel> transactionModels = readTransactionsFile();
		return getCustomerRewards(transactionModels, customerId);
	}

	private List<TransactionModel> readTransactionsFile() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<TransactionModel> transactionModels = mapper.readValue(transactionFile.getFile(), new TypeReference<List<TransactionModel>>(){});
		return transactionModels;
	}

	@Override
	public List<RewardModel> getAllRewardDetails() throws  IOException {
		List<TransactionModel> transactionModels = readTransactionsFile();
		List<RewardModel> rewardDetails = new ArrayList<RewardModel>();
		if (!CollectionUtils.isEmpty(transactionModels)) {
			Set<Long> customerIds = transactionModels.stream().distinct().map(p -> p.getCustomerId())
					.collect(Collectors.toSet());
			for (Long customerId : customerIds) {
				RewardModel rewardModel = getCustomerRewards(transactionModels, customerId);
				rewardDetails.add(rewardModel);
			}

		}

		return rewardDetails;
	}

	private RewardModel getCustomerRewards(List<TransactionModel> transactionModels, Long customerId) {
		RewardModel rewardModel = new RewardModel();
		rewardModel.setCustomerId(customerId);
		List<TransactionModel> transactions = transactionModels.stream()
				.filter(t -> t.getCustomerId().equals(customerId)).collect(Collectors.toList());
		rewardModel.setCustomerName(transactions.get(0).getCustomerName());
		Map<String, Long> monthlyRewards = new HashMap<>();
			Set<String> months = transactionModels.stream().distinct().map(p -> p.getTransactionDate())
					.collect(Collectors.toSet());
			for(String month: months) {
				List<TransactionModel> monthlyTransactions = transactions.stream()
						.filter(t -> t.getTransactionDate().equals(month)).collect(Collectors.toList());
				monthlyRewards.put(month, getMonthlyRewards(monthlyTransactions));
				
			}
		rewardModel.setMonthlyRewards(monthlyRewards);
		rewardModel.setTotalRewards(monthlyRewards.values().stream().mapToLong(Long::valueOf).sum());
		return rewardModel;
	}

	private Long getMonthlyRewards(List<TransactionModel> monthlyTransactions) {
		Long rewards = 0L;
		for(TransactionModel transaction: monthlyTransactions) {
			rewards= rewards+calculateReward(transaction);
		}
		return rewards;
	}

	private Long calculateReward(@Valid TransactionModel transactionModel) {
		Long rewardPoints = 0L;
		BigDecimal eligibleAmount = BigDecimal.ZERO;
		if(Constants.BIGDECIMAL_HUNDRED.compareTo(transactionModel.getAmount()) == -1) {
			eligibleAmount = transactionModel.getAmount().subtract(Constants.BIGDECIMAL_HUNDRED);
			rewardPoints = (2*eligibleAmount.longValue())+50L;
		} else if(Constants.BIGDECIMAL_FIFTY.compareTo(transactionModel.getAmount()) == -1) {
			eligibleAmount = transactionModel.getAmount().subtract(Constants.BIGDECIMAL_FIFTY);
			rewardPoints = eligibleAmount.longValue();
		}
		return rewardPoints;
	}
}