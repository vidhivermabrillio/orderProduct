package com.brillio.order.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.brillio.order.model.Product;
import com.brillio.order.repo.ProductRepository;

import org.springframework.stereotype.Service;

import com.brillio.order.model.User;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	 @Override
	    public Iterable<Product> getAllProducts() {
	        return productRepository.findAll();
	    }

	    @Override
	    public Product getProduct(int id) {
	    	
	    	Optional<Product> p = productRepository.findById(id);
	        return p.get();
	          //.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	    }

	    @Override
	    public Product save(Product product) {
	        return productRepository.save(product);
	    }

}
