package com.brillio.order.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brillio.order.controller.OrderController.OrderForm;
import com.brillio.order.model.Order;
import com.brillio.order.model.User;
import com.brillio.order.repo.IOrderRepo;
import com.brillio.order.repo.UserRepository;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	public IOrderRepo orderRepo;
	
	@Autowired
	public UserRepository userRepo;

	@Override
	public Order saveOrder(Order order) {
					
		Optional<User> user = userRepo.findById(order.getUserId());
		order.setUserId(user.get().getUserId());
		//order.setUserId(1);
		order.setOrderedAt(LocalDate.now());
		return orderRepo.save(order);
	}

	public List<Order> getAllOrder(int userId) {
		return orderRepo.findAllByUserID(userId);
	}
	
	@Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepo.findAll();
    }
	
	@Override
    public void update(Order order) {
        this.orderRepo.save(order);
    }

//	@Override
//	public Order create(@NotNull(message = "The order cannot be null.") @Valid Order order) {
//		  order.setOrderedAt(LocalDate.now());
//	      return this.orderRepo.save(order);
//	}

}
