package com.linxi.shop.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  @Column(name="productid")	  
	  private Long productid;
	  
	  @NotNull
	  @Column(name = "userid")
	  private Long userid;
	  
	  @NotNull
	  @Column(name = "name")
	  private String name;
	  	  
	  @NotNull
	  @Column(name = "catalog")
	  private String catalog;
	  
	  @NotNull
	  @Column(name = "format")
	  private String format;
	  
	  @NotNull
	  @Column(name = "cost")
	  private String cost;
	
	  @NotNull
	  @Column(name = "sellprice")
	  private String sellprice;
		
	  @NotNull
	  @Column(name = "description")
	  private String description;
	  
	  

	public Product() {
		super();
	}
	
	public Product(long productid) {
		this.productid = productid;
	}

	public Product(Long productid, String name, String catalog, String format, String cost, String sellprice,
			String description) {
		super();
		this.productid = productid;
		this.name = name;
		this.catalog = catalog;
		this.format = format;
		this.cost = cost;
		this.sellprice = sellprice;
		this.description = description;
	}

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getSellprice() {
		return sellprice;
	}

	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	


  
}

