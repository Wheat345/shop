package com.linxi.shop.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

public class TransactionForUI {
	 
	  private Long transactionid;
	  
	  private Long userid;
	  
	  private Long customerid;

	  private String productids;
	  
	  private String created;
	  	  
	  private String paymentmethod;
	  
	  private String details;
	  

	public TransactionForUI() {
		super();
	}

	public TransactionForUI(Transaction transcation) {
		this.transactionid = transcation.getCustomerid();
		this.userid = transcation.getUserid();
		this.customerid = transcation.getCustomerid();
		this.productids = transcation.getProductids();
		this.created = transcation.getCreated().toString();
		this.paymentmethod = transcation.getPaymentmethod();
		this.details = transcation.getDetails();
				
	}
} 
