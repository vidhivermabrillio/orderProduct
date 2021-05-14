package com.brillio.order.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.brillio.order.controller.OrderController.OrderForm;
import com.brillio.order.model.OrderProduct;
import org.springframework.stereotype.Service;

import com.brillio.order.model.User;

@Service
public interface OrderProductService {

    OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);
	public @NotNull OrderForm getproductsByOrderId(int orderId) ;

}
