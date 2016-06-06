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

import com.mycompany.ppms.entity.Product;
import com.mycompany.ppms.service.CustomerOrderService;
import com.mycompany.ppms.service.ProductOrderService;

@Controller
@RequestMapping(value = "/customerOrder")
public class CustomerOrderController {

	@Autowired
	@Qualifier("customerOrderService")
	private CustomerOrderService customerOrderService;
	
	@Autowired
	@Qualifier("productOrderService")
	private ProductOrderService productOrderService;
	
	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> getCustomerOrderProducts(@PathVariable ("id") Long id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Product> orderedProducts = productOrderService.getAllProductsOfCustomerOrderByCustomerOrderId(id);
		
		jsonMap.put("orderedProducts", orderedProducts);
		return jsonMap;
	}
	
}
