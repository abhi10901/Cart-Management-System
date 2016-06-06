package com.mycompany.ppms.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.ppms.entity.CustomerOrder;
import com.mycompany.ppms.entity.Product;

@Repository("customerOrderDAO")
@Transactional(readOnly = true)
public class CustomerOrderDAOImpl implements CustomerOrderDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<CustomerOrder> getCustomerOrdersByCustomerId(Long customerId) {
		TypedQuery<CustomerOrder> customerOrderQuery = entityManager.createNamedQuery("CustomerOrder.findByCustomerId", CustomerOrder.class);
		customerOrderQuery.setParameter("customerId", customerId);
		
		List<CustomerOrder> customerOrders = customerOrderQuery.getResultList();
		return customerOrders;
	}

}
