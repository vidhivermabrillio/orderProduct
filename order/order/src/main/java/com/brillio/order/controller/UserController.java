package com.brillio.order.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.order.model.User;
import com.brillio.order.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	Map<String, String> map = new HashMap<>();
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) throws ServletException {
		
		try {
		User userObj = validateUser(user.getUserName(), user.getPassword());
		map.clear();
		map.put("isAdmin", ""+ userObj.isAdmin());
		map.put("userId", ""+ userObj.getUserId());
		map.put("createdAt", ""+userObj.getCreatedAt());
		map.put("userName", ""+ userObj.getUserName());
		map.put("isSuccess", ""+ true);
		map.put("message", "user successfully logged in");
		}
		catch(Exception e) {
			String exceptionMessage = e.getMessage();
			map.clear();
			map.put("isSuccess", ""+ false);
			map.put("message", exceptionMessage);
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	private User validateUser(String username, String password) throws Exception {
		if (username == null || password == null) {
			throw new ServletException("Please fill in username and password");
		}
		User user = userService.validate(username, password);
		if (user == null) {
			throw new ServletException("Invalid credentials.");
		}
		return user;
	}
	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody User user) throws ServletException {
		
		try {
			if(validateCreateUser(user.getUserName(), user.getPassword())) {
				user.setCreatedAt(new Date());
				user.setUserId(new Random().nextInt());
				User createdUser = userService.createUser(user);
				if(createdUser != null) {
					map.clear();
					map.put("isSuccess", ""+ true);
					map.put("message", "user successfully logged in");					
				}			
			}
		}
		catch(Exception e) {
			String exceptionMessage = e.getMessage();
			map.clear();
			map.put("isSuccess", ""+ false);
			map.put("message", exceptionMessage);
			return new ResponseEntity<>(map, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	private boolean validateCreateUser(String userName, String pass) throws ServletException {
		if (userName == null || pass == null) {
			throw new ServletException("Please fill in username and password.");
		}
		User user = userService.findByUserName(userName);
		if (user != null) {
			throw new ServletException("User already exists.");
		}
		return true;
	}

}
