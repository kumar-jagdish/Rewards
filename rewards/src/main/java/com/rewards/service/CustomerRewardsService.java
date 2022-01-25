/**
 * 
 */
package com.rewards.service;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.model.CustomerMonthlyPointsInfo;
import com.rewards.model.CustomerRewardsInfo;
import com.rewards.model.CustomerTransactions;
import com.rewards.repository.CustomerTransactionsRepository;
import com.rewards.util.LogMessages;
import com.rewards.util.RewardsConstants;
/**
 * Service class for rewards operations.
 * @author jagdishk
 *
 */
@Service
public class CustomerRewardsService {

	@Autowired
	private CustomerTransactionsRepository customerTransactionsRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerRewardsService.class);
	
	/**
	 * Get monthly rewards points summary by customer Id.
	 * @param customerId
	 * @return
	 */
	public CustomerRewardsInfo getCustomerRewardsPointsById(Integer customerId) throws Exception {
		CustomerRewardsInfo customerRewardsInfo = null;
		// Fetch all the customer purchase transactions.
		List<CustomerTransactions> transactions = customerTransactionsRepository.findByCustomerIdOrderByPurchaseDateDesc(customerId);
		// Validate if customers purchase transactions are not null/ empty.
		if (transactions != null && !transactions.isEmpty()) {
			logger.info(LogMessages.PURCHASE_TRANSACTIONS_FOUND_SUCCESS);
			// Create a map of YearMonth and transactions specific to it.
			Map<YearMonth, List<CustomerTransactions>> yearMonthMap = transactions.stream()
					.collect(Collectors.groupingBy(CustomerTransactions::getPurchaseYearMonth));
			// Create monthly summary of points for each YearMonth.
			List<CustomerMonthlyPointsInfo> monthlySummary = yearMonthMap.entrySet().stream()
					.map(e -> CustomerMonthlyPointsInfo.builder().yearMonth(e.getKey())
							.points(getRewardPoints(e.getValue())).build())
					.collect(Collectors.toList());
			 
			// Build customer rewards info containing all the months and points for a specific customer.
			customerRewardsInfo = CustomerRewardsInfo.builder().customerId(customerId).monthlySummary(monthlySummary)
					.totalPoints(monthlySummary.stream().mapToInt(CustomerMonthlyPointsInfo::getPoints).sum()).build();
		} else {
			logger.info(LogMessages.NO_PURCHASE_TRANSACTIONS_FOUND);
			customerRewardsInfo = CustomerRewardsInfo.builder().customerId(customerId).monthlySummary(null)
					.totalPoints(0).build();
		}

		return customerRewardsInfo;
	}

	
	/**
	 * Get customer reward points for a specific month.
	 * @param transactions
	 * @return
	 */
	private Integer getRewardPoints(List<CustomerTransactions> transactions) {
		int totalAmount = 0;
		int totalPoints = 0;
		for (CustomerTransactions transaction : transactions) {
			totalAmount = transaction.getTotalAmount().intValue();
			if (totalAmount > RewardsConstants.REWARD_RANGE_RULE_2) {
				totalPoints = totalPoints
						+ (RewardsConstants.REWARD_RANGE_RULE_2 - RewardsConstants.REWARD_RANGE_RULE_1)
								* RewardsConstants.REWARD_POINTS_RULE_1
						+ (totalAmount - RewardsConstants.REWARD_RANGE_RULE_2) * RewardsConstants.REWARD_POINTS_RULE_2;
			} else if (totalAmount > RewardsConstants.REWARD_RANGE_RULE_1) {
				totalPoints = totalPoints
						+ (totalAmount - RewardsConstants.REWARD_RANGE_RULE_1) * RewardsConstants.REWARD_POINTS_RULE_1;
			}
		}
		return totalPoints;
	}
	
}
