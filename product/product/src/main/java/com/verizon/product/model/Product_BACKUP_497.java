package com.verizon.product.model;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "product_name")
	private String productName;
	@Column(name = "product_type")
	private String productType;
	@Column(name = "product_cost")
	private int productCost;
	
	
	
	public Product() {
		super();
	}


	public Product(int id, String productName, String productType, int productCost, int order_id) {
		super();
		this.id = id;
		this.productName = productName;
		this.productType = productType;
		this.productCost = productCost;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public int getProductCost() {
		return productCost;
	}


	public void setProductCost(int productCost) {
		this.productCost = productCost;
	}
	
}
