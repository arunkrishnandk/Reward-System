package com.retail.reward.model;

import java.util.Map;

import lombok.Data;

@Data
public class RewardModel {
	
	private Long customerId;
	
	private String customerName;
	
	private Map<String, Long> monthlyRewards;
	
	private Long totalRewards;

}
