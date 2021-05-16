package com.brillio.order.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mysql.cj.jdbc.Clob;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "ORDER_TB")
@ApiModel(value = "Order Details", description = "Order Details with user id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderProducts")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	@ApiModelProperty(name = "Id", value = "Unique order Id", dataType = "int", example = "101")
	private int orderId;

	@Column(name = "ORDERED_AT")
	@ApiModelProperty(name = "ordered at", value = "Order creation date", dataType = "date", example = "16-05-2021")
	private LocalDate orderedAt;

	@Column(name = "USER_ID")
	@ApiModelProperty(name = "user id", value = "Order by user id", dataType = "int", example = "1")
	private int userId;

	@JsonManagedReference
	@OneToMany(mappedBy = "pk.order")
	@Valid
	private List<OrderProduct> orderProducts = new ArrayList<>();

	@Transient
	public Double getTotalOrderPrice() {
		double sum = 0D;
		List<OrderProduct> orderProducts = getOrderProducts();
		for (OrderProduct op : orderProducts) {
			sum += op.getTotalPrice();
		}
		return sum;
	}

	@Transient
	public int getNumberOfProducts() {
		return this.orderProducts.size();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(LocalDate localDate) {
		this.orderedAt = localDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

}
