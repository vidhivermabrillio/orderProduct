package com.brillio.order.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "Order Product Details", description = "Order - Products list with quantity")
public class OrderProduct {

	@EmbeddedId
	@JsonIgnore
	@ApiModelProperty(name = "Id", value = "Composite key of Order and Product id ")
	private OrderProductPK pk;

	@Column(nullable = false)
	@ApiModelProperty(name = "quantity", value = "Product quantity", dataType = "int", example = "5")
	private Integer quantity;

	public OrderProduct() {
		super();
	}

	public OrderProduct(Order order, Product product, Integer quantity) {
		pk = new OrderProductPK();
		pk.setOrder(order);
		pk.setProduct(product);
		this.quantity = quantity;
	}

	@Transient
	public Product getProduct() {
		return this.pk.getProduct();
	}

	@Transient
	public Double getTotalPrice() {
		return (double) (getProduct().getProductCost() * getQuantity());
	}

	public OrderProductPK getPk() {
		return pk;
	}

	public void setPk(OrderProductPK pk) {
		this.pk = pk;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
