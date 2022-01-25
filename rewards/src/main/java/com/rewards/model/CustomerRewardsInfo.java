package com.rewards.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

/**
 * This class pojo reperesents rewards info for a customer.
 * @author jagdishk
 *
 */
@Builder
@Getter
public class CustomerRewardsInfo {	
	
	/**
	 * Customer Id.
	 */
	private Integer customerId;
	/**
	 * List of monthly summary of points.
	 */
	private List<CustomerMonthlyPointsInfo> monthlySummary; 

	/**
	 * Total points earned by customer.
	 */
	private Integer totalPoints;
		
}
