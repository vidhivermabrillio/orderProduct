package com.brillio.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brillio.order.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.userName = (?1) and u.password = (?2)")
	User validate(String username, String password);

	@Query("select u from User u where u.userName = (?1)")
	User findByUserName(String userName);

}
