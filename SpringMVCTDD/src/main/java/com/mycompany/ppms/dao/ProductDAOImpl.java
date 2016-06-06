package com.mycompany.ppms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.ppms.entity.Product;
import com.mycompany.ppms.exception.ProductNotFoundException;

@Repository("productDAO")
@Transactional(readOnly = true)
public class ProductDAOImpl implements ProductDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	private Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	
	@Override
	public List<Product> findByNameContains(String name) {
		//Product product = null;
		
		TypedQuery<Product> namedQuery = entityManager.createNamedQuery("Product.findByNameContains", Product.class);
		namedQuery.setParameter("name", "%" + name + "%");
		List<Product> products = namedQuery.getResultList();//new ArrayList<>();
		
		/*
		if(name.contains("Very Nice Shoes")) {
			product = new Product("Very Nice Shoes");
			product.setDescription("Very Nice Shoes made in Thailand");
			products.add(product);
		}*/
		
		return products;
	}

	@Override
	@Transactional(readOnly = false)
	public Product storeProduct(Product product) {
		if(product.equals(null)) return null;
		
		Product newProduct = null;
		try{
			newProduct = entityManager.merge(product);
        }catch(Exception e){
            e.printStackTrace();
            
        }
		
		return newProduct;
	}

	@Override
	public boolean deleteProductById(Long id) throws ProductNotFoundException {
		Product product = entityManager.find(Product.class, id);
		if(null == product) {
			throw new ProductNotFoundException(id+"");
		}
			
		try {
			entityManager.remove(product);
			logger.info("Product successfully deleted with Product Id : " + id);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		
		return true;
	}
}
