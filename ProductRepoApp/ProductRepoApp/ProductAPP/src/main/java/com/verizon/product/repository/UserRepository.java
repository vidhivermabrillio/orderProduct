package com.verizon.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verizon.product.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUserName(String user_name);
}
