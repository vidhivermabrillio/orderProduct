package com.verizon.product.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.verizon.product.model.Product;

public interface ProductService {
	
	Integer saveProduct(Product product);

	List<Product> getAllProduct();

	List<Product> getSearchedProductByName(String productName);

	Boolean checkUserExist(String userName);

	void save(MultipartFile file);

	InputStream load();

	

	

}
