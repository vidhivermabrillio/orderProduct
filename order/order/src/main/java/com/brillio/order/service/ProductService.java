package com.brillio.order.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.brillio.order.model.Product;


@Validated
@Service
public interface ProductService {

	public @NotNull Iterable<Product> getAllProducts();
	

	Product getProduct(@Min(value = 1L, message = "Invalid product ID.") int id);

	Product save(Product product);

}
