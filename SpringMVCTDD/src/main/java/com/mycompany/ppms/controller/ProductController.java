package com.mycompany.ppms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.ppms.entity.Product;
import com.mycompany.ppms.service.ProductService;

@Controller
@RequestMapping(value = "/product/")
public class ProductController {

	@Autowired
	@Qualifier("productService")
	private ProductService productService;

	private Logger log = LoggerFactory.getLogger(ProductController.class);

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> addProduct(HttpServletResponse response, Product product, Model model) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		response.setStatus(HttpStatus.SC_CREATED);

		Product newProduct = productService.storeProduct(product);

		jsonMap.put("status", "created");
		jsonMap.put("product", newProduct);
		return jsonMap;
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> updateProduct(Product product) {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		Product updatedProduct = productService.storeProduct(product);

		jsonMap.put("status", "updated");
		jsonMap.put("product", updatedProduct);
		return jsonMap;
	}
}
