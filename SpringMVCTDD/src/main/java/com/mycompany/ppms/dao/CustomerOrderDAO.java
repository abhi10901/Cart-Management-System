package com.mycompany.ppms.dao;

import java.util.List;

import com.mycompany.ppms.entity.CustomerOrder;
import com.mycompany.ppms.entity.Product;

public interface CustomerOrderDAO {
	public List<CustomerOrder> getCustomerOrdersByCustomerId(Long customerId);
}
