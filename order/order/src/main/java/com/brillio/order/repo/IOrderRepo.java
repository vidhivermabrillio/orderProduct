package com.brillio.order.repo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.brillio.order.controller.OrderController.OrderForm;
import com.brillio.order.model.Order;

public interface IOrderRepo extends CrudRepository<Order, String>{

	@Query("select o from Order o where o.userId = (?1)")
	List<Order> findAllByUserID(int userId);
}
