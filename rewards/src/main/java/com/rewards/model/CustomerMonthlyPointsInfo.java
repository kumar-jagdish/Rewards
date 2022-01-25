package com.rewards.model;


import java.time.YearMonth;

import lombok.Builder;
import lombok.Getter;

/**
 * This class contains points and year-month info.
 * @author jagdishk
 *
 */
@Builder
@Getter
public class CustomerMonthlyPointsInfo {

	/**
	 * Year and month of points.
	 */
	private YearMonth yearMonth;
	
	/**
	 * Points value accumulated for a specific month of year.
	 */
	private Integer points;
	
}
