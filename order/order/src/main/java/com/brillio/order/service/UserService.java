package com.brillio.order.service;

import com.brillio.order.model.User;

public interface UserService {
	
	public User validate(String username, String password);

}
