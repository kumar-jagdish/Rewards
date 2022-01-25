/**
 * 
 */
package com.rewards.model;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * This class represents purchase transaction entity data.
 * @author jagdishk
 *
 */
@Entity
@Data
@Table(name = "customer_transactions")
public class CustomerTransactions {
	
	@Id
	@Column(name = "id")
    private Long id;
    
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "purchase_details")
    private String purchaseDetails;

    @Column(name = "total_amount")
    private Double totalAmount;
    
    @Column(name = "purchase_date")
    private Date purchaseDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;

    private YearMonth purchaseYearMonth;
    
    public YearMonth getPurchaseYearMonth( ) {
    	this.purchaseYearMonth= YearMonth.from(this.purchaseDate.toInstant().atZone(ZoneId.systemDefault())
                .toLocalDate());
    	return purchaseYearMonth;
    }
}
