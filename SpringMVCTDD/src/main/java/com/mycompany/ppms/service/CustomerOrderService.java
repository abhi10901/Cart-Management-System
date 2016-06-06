package com.mycompany.ppms.service;

import java.util.List;

import com.mycompany.ppms.entity.*;

public interface CustomerOrderService {

	public List<CustomerOrder> getCustomerOrdersByCustomerId(Long customerId);
}
