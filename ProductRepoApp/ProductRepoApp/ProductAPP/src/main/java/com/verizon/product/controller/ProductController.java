package com.verizon.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.verizon.product.files.message.ResponseMessage;
import com.verizon.product.model.Product;
import com.verizon.product.service.ProductService;
import com.verizon.product.util.ExcelUploadUtil;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	@Qualifier(value = "productImpl")
	ProductService productService;
	
	
	@Autowired
	ExcelUploadUtil excelUploadUtil;

	/*
	 * @PostMapping("/login") public ResponseEntity validateUser(@Valid @RequestBody
	 * UserEntity user){ return ResponseEntity.ok("User is valid"); }
	 */

	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		ResponseEntity<String> resp = null;
		try {
			Integer productId = productService.saveProduct(product);
			resp = new ResponseEntity<String>("Poduct '" + productId + "' is successfully saved", HttpStatus.CREATED);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("product Id unable to save..", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;

	}

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProduct() {
		ResponseEntity<List<Product>> resp = null;
		List<Product> list = productService.getAllProduct();
		resp = new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		return resp;
	}

	@GetMapping("/product/{productName}")
	public ResponseEntity<?> getSearchedProduct(@RequestHeader(value = "user-name") String userName,
			@PathVariable String productName) {
		Boolean userExist = productService.checkUserExist(userName);
		if (userExist) {
			List<Product> ProductList = productService.getSearchedProductByName(productName);
			return new ResponseEntity<List<Product>>(ProductList, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("invalid user", HttpStatus.OK);
		}
	}
	
	
	@DeleteMapping("/product/{prodId}")
    public ResponseEntity<String> deleteProductById(@PathVariable int prodId){
		ResponseEntity<String> resp = null;
		try {
			productService.deleteProductById(prodId);
			resp = new ResponseEntity<String>("Poduct '" + prodId + "' is successfully saved", HttpStatus.CREATED);
		} catch (Exception e) {
			resp = new ResponseEntity<String>("Product '" + prodId +"'  unable to delete !!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return resp;
		
    }

	/*
	 * -------------Excelsheet code----------
	 */	
	
	@PostMapping("/excel/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		//ExcelUploadUtil excelUploadUtil=new ExcelUploadUtil();
		if (ExcelUploadUtil.hasExcelFormat(file)) {
			try {
				//System.out.println(file);
				
				productService.save(file);
				//System.out.println(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}


	@GetMapping("/download")
	public ResponseEntity<Resource> getFile() {
		String filename = "products.xlsx";
		InputStreamResource file = new InputStreamResource(productService.load());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
	

}


