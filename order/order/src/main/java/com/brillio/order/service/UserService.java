package com.brillio.order.service;


import org.springframework.stereotype.Service;

import com.brillio.order.model.User;

@Service
public interface UserService {
	
	public User validate(String username, String password);

}
