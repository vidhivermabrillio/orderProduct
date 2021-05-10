package com.brillio.order.controller;

import java.util.HashMap;
import java.util.Map;

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
		map.put("isAdmin", ""+ userObj.getIsAdmin());
		map.put("message", "user successfully logged in");
		}
		catch(Exception e) {
			String exceptionMessage = e.getMessage();
			map.clear();
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

}
