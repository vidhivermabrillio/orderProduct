package com.verizon.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.product.model.Product;
import com.verizon.product.model.User;
import com.verizon.product.repository.ProductRepository;
import com.verizon.product.repository.UserRepository;
import com.verizon.product.service.ProductService;

@Service(value = "productImpl")
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Integer saveProduct(Product product) {
		Integer ProductId=productRepository.save(product).getId();
		return ProductId;
	}


	@Override
	public List<Product> getAllProduct() {
		List<Product> list = productRepository.findAll();
		return list;
	}

	@Override
	public List<Product> getSearchedProductByName(String productName) {
		List<Product> productEntityList = productRepository.searchByProductNameLike(productName + "%");
		List<Product> ProductList = new ArrayList<>();
		for (Product productEntity : productEntityList) {
			Product Product = new Product();
			Product.setId(productEntity.getId());
			Product.setProductName(productEntity.getProductName());
			Product.setProductType(productEntity.getProductType());
			Product.setProductCost(productEntity.getProductCost());
			ProductList.add(Product);
		}
		return ProductList;
	}

	@Override
	public Boolean checkUserExist(String userName) {
		User byUserName = userRepository.findByUserName(userName);
		if (byUserName != null) {
			return true;
		} else {
			return false;
		}

	}

	
}
