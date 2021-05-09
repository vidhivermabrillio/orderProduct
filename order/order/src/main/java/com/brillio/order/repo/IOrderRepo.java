package com.brillio.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brillio.order.model.Order;

public interface IOrderRepo extends JpaRepository<Order, String>{

}
