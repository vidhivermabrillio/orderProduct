package com.brillio.order.model;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "product_tb")
@ApiModel(value = "Product Details", description = "Products list with name, type and cost")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	@ApiModelProperty(name = "Id", value = "Unique Product Id", dataType = "int", example = "101")
	private int id;
	@Column(name = "PRODUCT_NAME")
	@ApiModelProperty(name = "name", value = "Product name", dataType = "String", example = "Dell")
	private String productName;
	@Column(name = "PRODUCT_TYPE")
	@ApiModelProperty(name = "type", value = "Product type", dataType = "String", example = "Laptop")
	private String productType;
	@Column(name = "PRODUCT_COST")
	@ApiModelProperty(name = "cost", value = "Product cost", dataType = "int", example = "50000")
	private int productCost;

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
