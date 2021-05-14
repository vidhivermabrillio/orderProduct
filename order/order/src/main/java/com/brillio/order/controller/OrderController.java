package com.brillio.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brillio.order.dto.OrderProductDto;
import com.brillio.order.model.Order;
import com.brillio.order.model.OrderProduct;
import com.brillio.order.service.OrderProductService;
import com.brillio.order.service.OrderServiceImpl;
import com.brillio.order.service.ProductService;
import com.brillio.order.service.ProductServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	public OrderServiceImpl orderService;
	
	@Autowired
	 OrderProductService orderProductService;
	
	
	@Autowired
	ProductService productService;
	
//	@PostMapping(value = "/orderSave")
//	public ResponseEntity<Order> saveFavorite(@RequestBody Order order) {
//		System.out.println(order);
//		return new ResponseEntity<Order>(orderService.saveOrder(order), HttpStatus.CREATED);
//	}
	
	@GetMapping(value = "/orders/{userId}")
	public ResponseEntity<List<Order>> getAllFavorite(@PathVariable("userId") int userId) {
		return new ResponseEntity<List<Order>>(orderService.getAllOrder(userId), HttpStatus.OK);
	}
	
	@PostMapping(value = "/orderSave")
	public ResponseEntity<Order> create(@RequestBody OrderForm orderForm) {
		
		 List<OrderProductDto> formDtos = orderForm.getProductOrders();
		
		 Order newOrder = new Order();
		 newOrder.setUserId(orderForm.getUserId());
		 newOrder = this.orderService.saveOrder(newOrder);
		 List<OrderProduct> orderProducts = new ArrayList<>();
		 
		 for(OrderProductDto op:formDtos) {
			 orderProducts.add(orderProductService.create(new OrderProduct(newOrder, productService.getProduct(op
		              .getProduct().getId()), op.getQuantity())));
		 }
		 
		newOrder.setOrderProducts(orderProducts);
		this.orderService.update(newOrder);
		
		 return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
	}
	
	
//	@GetMapping(value = "/getProductsByOrderId/{orderId}")
//    @ResponseStatus(HttpStatus.OK)
//    public @NotNull OrderForm getProductsByOrderId(@PathVariable("orderId") int orderId) {
//        return this.orderProductService.getproductsByOrderId(orderId);
//    }
	
	
	@GetMapping(value = "/products/{orderId}")
	public ResponseEntity<OrderForm> getProductsByOrderId(@PathVariable("orderId") int orderId) {
		return new ResponseEntity<OrderForm>(this.orderProductService.getproductsByOrderId(orderId), HttpStatus.OK);
	}
	
	
	public static class OrderForm {
		
		private int userId;

        private List<OrderProductDto> productOrders;

        public List<OrderProductDto> getProductOrders() {
            return productOrders;
        }

        public void setProductOrders(List<OrderProductDto> productOrders) {
            this.productOrders = productOrders;
        }

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}
        
        
    }
	

}
