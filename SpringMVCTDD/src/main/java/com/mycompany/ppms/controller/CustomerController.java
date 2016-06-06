package com.mycompany.ppms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.ppms.entity.*;
import com.mycompany.ppms.service.*;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;
	
	@Autowired
	@Qualifier("customerOrderService")
	private CustomerOrderService customerOrderService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
						produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> findCustomerById(@PathVariable ("id") Long id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Customer customer = customerService.findCustomerById(id);
		resultMap.put("customer", customer);
		
		return resultMap;
	}
	
	@RequestMapping(value = "/{id}/order", method = RequestMethod.GET,
						produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getCustomerOrders(@PathVariable ("id") Long customerId) {
		Map<String, Object> jsonMap = new HashMap<>();
		List<CustomerOrder> customerOrders = customerOrderService.getCustomerOrdersByCustomerId(customerId);
		//System.out.println(customerOrders);
		jsonMap.put("customerOrdersCount", customerOrders.size());
		jsonMap.put("customerOrders", customerOrders);
		
		return jsonMap;
	}
}
