package com.mycompany.ppms.dao;

import java.util.List;

import com.mycompany.ppms.entity.Product;

public interface ProductOrderDAO {

	List<Product> getAllProductsOfCustomerOrderByCustomerOrderId(Long orderId);

}
