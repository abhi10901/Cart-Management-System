package com.mycompany.ppms.dao;

import java.util.List;

import com.mycompany.ppms.entity.Product;

public interface ProductDAO {

	public List<Product> findByNameContains(String name);
	public Product storeProduct(Product product);
	public boolean deleteProductById(Long id);
}
