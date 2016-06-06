package com.mycompany.ppms.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.ppms.dao.CustomerDAO;
import com.mycompany.ppms.entity.Customer;

@Service("customerService")
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	@Qualifier("customerDAO")
	private CustomerDAO customerDAO;
	
	@Override
	public Customer findCustomerById(Long id) {
		return customerDAO.findCustomerById(id);
	}

}
