package com.brillio.order.model;

import javax.persistence.*;


@Entity
@Table(name = "PRODUCT_TB")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	@Column(name = "PRODUCT_NAME")
	private String productName;
	@Column(name = "PRODUCT_TYPE")
	private String productType;
	@Column(name = "PRODUCT_COST")
	private int productCost;

//	@ManyToOne
//    @JoinColumn(name="ORDER_ID")
//    private Order orders;
	
	
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

//	public Order getOrders() {
//		return orders;
//	}
//
//	public void setOrders(Order orders) {
//		this.orders = orders;
//	}

}
