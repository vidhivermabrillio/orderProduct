package com.brillio.user.service;


import org.springframework.stereotype.Service;

import com.brillio.user.model.User;


@Service
public interface UserService {
	
	public User validate(String username, String password);
	
	public User createUser(User user);

	public User findByUserName(String userName);

}
