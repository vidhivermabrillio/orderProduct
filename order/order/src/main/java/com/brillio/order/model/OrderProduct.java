package com.brillio.order.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderProduct {
	
	@EmbeddedId
    @JsonIgnore
    private OrderProductPK pk;

    @Column(nullable = false)
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
