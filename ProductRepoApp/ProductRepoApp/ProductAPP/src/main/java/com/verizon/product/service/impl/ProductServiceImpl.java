package com.verizon.product.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.verizon.product.model.Product;
import com.verizon.product.model.User;
import com.verizon.product.repository.ProductRepository;
import com.verizon.product.repository.UserRepository;
import com.verizon.product.service.ProductService;
import com.verizon.product.util.ExcelUploadUtil;


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
		List<Product> productByNameList = productRepository.searchByProductNameLike(productName + "%");
		List<Product> list = new ArrayList<>();
		for (Product productList : productByNameList) {
			Product Product = new Product();
			Product.setId(productList.getId());
			Product.setProductName(productList.getProductName());
			Product.setProductType(productList.getProductType());
			Product.setProductCost(productList.getProductCost());
			list.add(Product);
		}
		return list;
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
	
	//---------Delete-------------
	@Override
	public void deleteProductById(int prodId) {
		productRepository.deleteById(prodId);

	}

	@Override
	public void save(MultipartFile file) {
	    try {
	      List<Product> products = ExcelUploadUtil.excelToProducts(file.getInputStream());
	     productRepository.saveAll(products);
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
		
	  }
	@Override
	  public ByteArrayInputStream load() {
	    List<Product> products = productRepository.findAll();

	    ByteArrayInputStream in = ExcelUploadUtil.productsToExcel(products);
	    return in;
	  }


	


	
	 
	
}
