package com.retail.reward.service;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import com.retail.reward.model.RewardModel;

@Service
public interface RewardService {

	List<RewardModel> getAllRewardDetails() throws IOException;

	RewardModel getRewardDetails(@NotNull Long customerId) throws IOException;

}
