package com.mycompany.ppms.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.ppms.entity.Product;


public interface ProductService {
	
	public List<Product> findByNameContains(String name);
	public Product storeProduct(Product product);
	public boolean deleteProductById(Long id);

}
