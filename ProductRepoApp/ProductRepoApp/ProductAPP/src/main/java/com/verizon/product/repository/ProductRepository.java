package com.verizon.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.verizon.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> searchByProductNameLike(String productName);
}
