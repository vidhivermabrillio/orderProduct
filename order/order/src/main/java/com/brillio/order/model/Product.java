package com.brillio.order.model;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "PRODUCT_TB")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
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

	@ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ORDER_ID")
    private Order order;

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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	

}
