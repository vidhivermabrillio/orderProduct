package com.brillio.order.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "ORDER_TB")
public class Order implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private int orderId;
	
	@Column(name="ORDERED_AT")
	private Date orderedAt;
	
	@Column(name="USER_ID")
	private int userId;
		
	@OneToOne(mappedBy = "order",cascade = {CascadeType.ALL})
    private User user;
	
	@OneToMany(mappedBy = "order",cascade = {CascadeType.ALL})
    private List<Product> products ;
//    = new ArrayList<>();

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderedAt() {
		return orderedAt;
	}

	public void setOrderedAt(Date orderedAt) {
		this.orderedAt = orderedAt;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
	

}
