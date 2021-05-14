package com.brillio.order.repo;

import org.springframework.data.repository.CrudRepository;

import com.brillio.order.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
