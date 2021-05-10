package com.verizon.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.product.model.Product;
import com.verizon.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	@Qualifier(value = "productImpl")
	ProductService productService;

	
	/*@PostMapping("/login")
	public ResponseEntity validateUser(@Valid @RequestBody UserEntity user){
		return ResponseEntity.ok("User is valid");
	}
*/
	
	
	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		ResponseEntity<String>  resp=null;
		try {
		Integer productId=productService.saveProduct(product);
		resp=new ResponseEntity<String>("Poduct '"+productId+"' is successfully saved",HttpStatus.CREATED);
		}
		catch (Exception e) {
			resp=new ResponseEntity<String>("product Id unable to save..",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;

	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProduct() {
		ResponseEntity<List<Product>> resp=null;
			List<Product> list = productService.getAllProduct();
			resp=new ResponseEntity<List<Product>>(list,HttpStatus.OK);
			return resp;
	}
	
	@GetMapping("/product/{productName}")
	public ResponseEntity<?> getSearchedProduct(@RequestHeader(value = "user-name") String userName, @PathVariable String productName) {
		Boolean userExist = productService.checkUserExist(userName);
		if(userExist) {
		List<Product> ProductList = productService.getSearchedProductByName(productName);
		return new ResponseEntity<List<Product>>(ProductList, HttpStatus.OK);
		}else{
			return new ResponseEntity<String>("invalid user", HttpStatus.OK);
		}
	}
	
}
