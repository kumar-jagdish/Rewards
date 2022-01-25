/**
 * 
 */
package com.rewards.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rewards.model.CustomerRewardsInfo;
import com.rewards.service.CustomerRewardsService;
/**
 * This is the controller class for fetching reward points for specific customer.
 * @author jagdishk
 *
 */
@RestController
@RequestMapping("/rewards/customer")
public class CustomerRewardsController {


	@Autowired
	private CustomerRewardsService customerRewardsService;

	/**
	 * Get customer reward points by Id. 
	 * @param customerId
	 * @return
	 */
	@RequestMapping("/get/{customerId}")
	public ResponseEntity<CustomerRewardsInfo> getCustomerRewardsPointsById(@PathVariable Integer customerId) throws Exception {
		CustomerRewardsInfo customerRewards = customerRewardsService.getCustomerRewardsPointsById(customerId);
		return ResponseEntity.ok().body(customerRewards);
	}
}
