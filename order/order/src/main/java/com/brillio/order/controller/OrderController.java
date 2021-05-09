package com.brillio.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.order.model.Order;
import com.brillio.order.service.OrderServiceImpl;

@RestController
@CrossOrigin("*")
public class OrderController {
	
	@Autowired
	public OrderServiceImpl orderService;
	
	@PostMapping(value = "/orderSave")
	public ResponseEntity<Order> saveFavorite(@RequestBody Order order) {
		System.out.println(order);
		return new ResponseEntity<Order>(orderService.saveOrder(order), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/orders")
	public ResponseEntity<List<Order>> getAllFavorite() {
		return new ResponseEntity<List<Order>>(orderService.getAllOrder(), HttpStatus.OK);
	}

}
