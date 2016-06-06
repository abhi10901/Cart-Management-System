package com.mycompany.ppms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.ppms.entity.Product;

@Repository("productOrderDAO")
@Transactional(readOnly = true)
public class ProductOrderDAOImpl implements ProductOrderDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Product> getAllProductsOfCustomerOrderByCustomerOrderId(Long orderId) {
		TypedQuery<Product> productsOfOrderQuery = entityManager.createNamedQuery("ProductOrder.findAllProductsByOrderId", Product.class);
		productsOfOrderQuery.setParameter("orderId", orderId);
		
		List<Product> orderdProducts = productsOfOrderQuery.getResultList();
		return orderdProducts;
	}
	
	

}
