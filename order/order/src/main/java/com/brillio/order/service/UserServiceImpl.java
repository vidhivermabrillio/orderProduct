package com.brillio.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brillio.order.model.User;
import com.brillio.order.repo.UserRepository;

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User validate(String username, String password) {

		User user = userRepository.validate(username, password);
		return user;
		
	}
	
	@Override
	public User createUser(User user) {		
		return userRepository.save(user);		
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}
