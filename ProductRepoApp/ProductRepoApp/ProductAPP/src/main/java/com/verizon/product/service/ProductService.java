package com.verizon.product.service;

import java.util.List;

import com.verizon.product.model.Product;

public interface ProductService {
	
	Integer saveProduct(Product product);

	List<Product> getAllProduct();

	List<Product> getSearchedProductByName(String productName);

	Boolean checkUserExist(String userName);

}
