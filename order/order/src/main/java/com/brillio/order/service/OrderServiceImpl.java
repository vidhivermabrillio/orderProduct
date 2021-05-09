package com.brillio.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brillio.order.model.Order;
import com.brillio.order.repo.IOrderRepo;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	public IOrderRepo orderRepo;

	public Order saveOrder(Order order) {
		return orderRepo.save(order);
	}

}
