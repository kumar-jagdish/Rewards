package com.rewards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rewards.model.CustomerRewardsInfo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RewardsApplicationTests {

	@LocalServerPort
	private int port;	
	
	@Autowired
	private TestRestTemplate restTemplate;

	private final String API_CUSTOMER_SUMMARY = "rewards/customer/get";
	
	@Test
	void customerRewardsEndPointTest1() throws Exception {
		String baseUrl = "http://localhost:" + port + "/";		
		ResponseEntity<CustomerRewardsInfo> responseEntity = restTemplate.getForEntity(baseUrl + API_CUSTOMER_SUMMARY +"/2", CustomerRewardsInfo.class);
		assert(responseEntity.getStatusCode() == HttpStatus.OK);
		CustomerRewardsInfo rewardsInfo = responseEntity.getBody();
		assert(rewardsInfo != null  && rewardsInfo.getTotalPoints() > 0);
	}
	

	@Test
	void customerRewardsEndPointTest2() throws Exception {
		String baseUrl = "http://localhost:" + port + "/";		
		ResponseEntity<CustomerRewardsInfo> responseEntity = restTemplate.getForEntity(baseUrl + API_CUSTOMER_SUMMARY +"/5", CustomerRewardsInfo.class);
		assert(responseEntity.getStatusCode() == HttpStatus.OK);
		CustomerRewardsInfo rewardsInfo = responseEntity.getBody();
		assert(rewardsInfo != null && rewardsInfo.getTotalPoints() == 0);
	}
	
	@Test
	void customerRewardsEndPointTest3() throws Exception {
		String baseUrl = "http://localhost:" + port + "/";		
		ResponseEntity<CustomerRewardsInfo> responseEntity = restTemplate.getForEntity(baseUrl + "/5", CustomerRewardsInfo.class);
		assert(responseEntity.getStatusCode() == HttpStatus.NOT_FOUND);
	
	}
}
