package com.brillio.order.model;

import javax.persistence.Embeddable;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Embeddable
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "order")
public class OrderProductPK implements Serializable {

	@JsonBackReference
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "ORDER_ID")
	private Order order;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "ID")
	private Product product;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	// hashcode() and equals() methods
}
