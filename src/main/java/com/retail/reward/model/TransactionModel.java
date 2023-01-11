package com.retail.reward.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class TransactionModel implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6656622812993891568L;

	private BigDecimal amount;
	
	private String customerName;
	
	private Long customerId;
	
	private String transactionDate;

}
