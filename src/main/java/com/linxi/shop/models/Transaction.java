package com.linxi.shop.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name="transactionid")	  
	  private Long transactionid;
	  
	  @NotNull
	  @Column(name = "userid")
	  private Long userid;
	  
	  @NotNull
	  @Column(name = "customerid")
	  private Long customerid;

	  @NotNull
	  @Column(name = "productids")
	  private String productids;
	  
	  @NotNull
	  @Column(name = "created")
	  private Date created;
	  	  
	  @NotNull
	  @Column(name = "paymentmethod")
	  private String paymentmethod;
	  
	  @NotNull
	  @Column(name = "details")
	  private String details;
	  

	public Transaction() {
		super();
	}
	
	public Transaction(long transactionid) {
		this.transactionid = transactionid;
	}

	public Transaction(Long transactionid, Long customerid, String productids, Date created, String paymentmethod,
			String details) {
		super();
		this.transactionid = transactionid;
		this.customerid = customerid;
		this.productids = productids;
		this.created = created;
		this.paymentmethod = paymentmethod;
		this.details = details;
	}

	public Long getTransactionid() {
		return transactionid;
	}

	public void setTransactionid(Long transactionid) {
		this.transactionid = transactionid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public String getProductids() {
		return productids;
	}

	public void setProductids(String productids) {
		this.productids = productids;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
} 
