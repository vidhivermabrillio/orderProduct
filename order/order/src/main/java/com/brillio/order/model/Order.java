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

@Entity
@Table(name = "ORDER_TB")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="orderProducts")
public class Order{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private int orderId;
	
	@Column(name="ORDERED_AT")
	private LocalDate orderedAt;
	
	@Column(name="USER_ID")
	private int userId;
	
//	 @OneToMany(mappedBy="orders")
//	 private List<Product> products;
	
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

//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}

	


}
