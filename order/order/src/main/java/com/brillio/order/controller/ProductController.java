package com.brillio.order.controller;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.order.model.Product;
import com.brillio.order.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/products")
@Api(value = "Product Controller will have save and retrieve products operations")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = { "", "/" })
    @ApiOperation(value = "Get product list" ,notes="Return all Products available in the System", response = Product.class)
    public @NotNull Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }
}