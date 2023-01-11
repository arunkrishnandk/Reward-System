package com.retail.reward.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.reward.model.RewardModel;
import com.retail.reward.service.RewardService;


@RestController
@RequestMapping("/")
public class RewardController {
	
	@Autowired
	RewardService rewardServiceImpl;
	
	@GetMapping(value = "rewards")
	public ResponseEntity<List<RewardModel>> getAllRewardDetails() throws IOException {
		
		return ResponseEntity.ok(rewardServiceImpl.getAllRewardDetails());

	}
	
	@GetMapping(value = "customer-rewards")
	public ResponseEntity<RewardModel>  getRewardDetails(@RequestParam @NotNull Long id) {
		
		try {
			return ResponseEntity.ok(rewardServiceImpl.getRewardDetails(id));
		} catch (IOException e) {
			return ResponseEntity.badRequest().body(null);
		}

	}
	
//	@GetMapping(value = "/metrics")
//	public ResponseEntity<List<MetricModel>> getAllMetricDetails() {
//		
//		return ResponseEntity.ok(metricServiceImpl.getAllMetricDetails());
//
//	}
//	
//	@GetMapping(value = "/metrics/{id}")
//	public ResponseEntity<MetricModel>  getMetricDetails(@PathVariable @NotNull int id) {
//		
//		return ResponseEntity.ok(metricServiceImpl.getMetricDetails(id));
//
//	}

}
