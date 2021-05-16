package com.brillio.order.repo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.brillio.order.controller.OrderController.OrderForm;
import com.brillio.order.model.OrderProduct;
import com.brillio.order.model.OrderProductPK;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
	
	@Query("select op from OrderProduct op join fetch op.pk.order where op.pk.order.orderId = (?1)")
	@NotNull List<OrderProduct> getproductsByOrderId(int orderId);
}
