/**
 * 
 */
package com.rewards.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rewards.model.CustomerTransactions;
/**
 * This is crud repository for database operations.
 * @author jagdishk
 *
 */
@Repository
public interface CustomerTransactionsRepository extends CrudRepository <CustomerTransactions, Long> {

	public List<CustomerTransactions> findByCustomerIdOrderByPurchaseDateDesc(Integer customerId);

}
