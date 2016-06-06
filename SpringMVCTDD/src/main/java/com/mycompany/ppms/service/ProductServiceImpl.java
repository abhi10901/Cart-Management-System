package com.mycompany.ppms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.ppms.dao.ProductDAO;
import com.mycompany.ppms.entity.Product;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	@Qualifier("productDAO")
	private ProductDAO productDAO;
	
	@Override
	public List<Product> findByNameContains(String name) {
		return productDAO.findByNameContains(name);
	}

	@Override
	public Product storeProduct(Product product) {
		return productDAO.storeProduct(product);
	}

	@Override
	public boolean deleteProductById(Long id) {
		return productDAO.deleteProductById(id);
	}

}
