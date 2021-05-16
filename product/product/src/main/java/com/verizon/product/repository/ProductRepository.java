package com.verizon.product.repository;

import java.io.InputStream;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.verizon.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	List<Product> searchByProductNameLike(String productName);
	
	//void save(MultipartFile file);

	//InputStream load();

}
