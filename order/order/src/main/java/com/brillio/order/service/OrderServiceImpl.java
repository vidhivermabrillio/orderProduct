package com.brillio.order.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brillio.order.model.Order;
import com.brillio.order.repo.IOrderRepo;

@Service
public class OrderServiceImpl implements IOrderService{
	
	@Autowired
	public IOrderRepo orderRepo;

	@Override
	public Order saveOrder(Order order) {
		
		
		
		
		// Prep Work
//				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//				
//				//save example - without transaction
//				Session session = sessionFactory.openSession();
//				long id = (Long) session.save(order);
//				System.out.println("save called without transaction, id="+id);
//				session.flush(); //address will not get saved without this
//				System.out.println("*****");
//				
//				
				
		return orderRepo.save(order);
	}

	public List<Order> getAllOrder(int userId) {
		return orderRepo.findAllByUserID(userId);
	}

}
