package com.mycompany.ppms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.ppms.dao.CustomerOrderDAO;
import com.mycompany.ppms.entity.CustomerOrder;
import com.mycompany.ppms.entity.Product;

@Service("customerOrderService")
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService{

	@Autowired
	@Qualifier("customerOrderDAO")
	private CustomerOrderDAO customerOrderDAO;
	
	@Override
	public List<CustomerOrder> getCustomerOrdersByCustomerId(Long customerId) {
		return customerOrderDAO.getCustomerOrdersByCustomerId(customerId);
	}

}
