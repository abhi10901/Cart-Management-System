package com.mycompany.ppms.dao;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.ppms.entity.Customer;
import com.mycompany.ppms.exception.EntityObjectNotFoundException;

@Repository("customerDAO")
@Transactional(readOnly = true)
public class CustomerDAOImpl implements CustomerDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	private Logger logger = LoggerFactory.getLogger(CustomerDAOImpl.class);
	
	@Override
	public Customer findCustomerById(Long id) {
		logger.info("Getting Customer by id : " + id);
		Customer customer = entityManager.find(Customer.class, id);
		if(customer == null) {
			new EntityObjectNotFoundException(id, "Customer");
		}
		return customer;
	}

	@Override
	public Customer storeCustomer(Customer customer) {
		Customer newCustomer = null;
		try {
			newCustomer = entityManager.merge(customer);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return newCustomer;
	}

	@Override
	public boolean removeCustomerById(Long id) {
		Customer customerToDelete = findCustomerById(id);
		try {
			entityManager.remove(customerToDelete);
		} catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

}
