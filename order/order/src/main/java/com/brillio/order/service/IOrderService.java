package com.brillio.order.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.brillio.order.model.Order;

@Validated
@Service
public interface IOrderService {
	
	public Order saveOrder(Order order);	
	
	@NotNull Iterable<Order> getAllOrders();

    //Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

    void update(@NotNull(message = "The order cannot be null.") @Valid Order order);

	public List<Order> getAllOrder(int userId);

}
