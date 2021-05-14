package com.brillio.order.service;

import java.util.List;

import javax.transaction.*;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brillio.order.controller.OrderController.OrderForm;
import com.brillio.order.dto.OrderProductDto;
import com.brillio.order.model.OrderProduct;
import com.brillio.order.repo.OrderProductRepository;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

	@Autowired
    private OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }

	@Override
	public @NotNull OrderForm getproductsByOrderId(int orderId) {
		List<OrderProduct> opList = this.orderProductRepository.getproductsByOrderId(orderId);
		OrderForm form = new OrderForm();
		List<OrderProductDto> orderProductList = null;
		for(OrderProduct op:opList) {
			OrderProductDto orderProductDto = null;
			orderProductDto.setProduct(op.getPk().getProduct());
			orderProductDto.setQuantity(op.getQuantity());
			orderProductList.add(orderProductDto);
			
		}
		form.setProductOrders(orderProductList);
		return form;
	}
}
