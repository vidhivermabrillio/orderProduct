package com.brillio.order.service;

import java.util.List;

import com.brillio.order.model.Order;

public interface IOrderService {
	
	public Order saveOrder(Order order);
	public List<Order> getAllOrder();

}
