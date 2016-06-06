package com.mycompany.ppms.service;

import java.util.List;

import com.mycompany.ppms.entity.Product;

public interface ProductOrderService {

	public List<Product> getAllProductsOfCustomerOrderByCustomerOrderId(Long orderId);

}
