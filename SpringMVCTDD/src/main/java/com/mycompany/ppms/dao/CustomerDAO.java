package com.mycompany.ppms.dao;

import com.mycompany.ppms.entity.Customer;

public interface CustomerDAO {

	public Customer findCustomerById(Long id);
	public Customer storeCustomer(Customer customer);
	public boolean removeCustomerById(Long id);
}
