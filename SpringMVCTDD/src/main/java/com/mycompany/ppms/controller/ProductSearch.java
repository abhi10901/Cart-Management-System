package com.mycompany.ppms.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mycompany.ppms.entity.Product;
import com.mycompany.ppms.service.ProductService;

@Controller
public class ProductSearch {

	@Autowired
	private ProductService productService;

	final Logger logger = LoggerFactory.getLogger(ProductSearch.class);
	/*
	 * final static List<String> products = new ArrayList<String>();
	 * 
	 * static { products.add("{\"name\":\"Very Nice Shoes\", " +
	 * "\"description\":\"Very nice shoes made in Thailand.\"}"); products.add(
	 * "{\"name\":\"Cool Shoes\", " +
	 * "\"description\":\"Cool shoes made in Japan.\"}"); }
	 */

	@RequestMapping(value = "/product/search", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> productSearch(@RequestParam ("q") String q)//HttpServletRequest request, HttpServletResponse response) 
								throws IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		String keyword = StringUtils.stripToEmpty(q);
		String infoText = String.format("Could not find any product matches '%s'", keyword);

		logger.info("productSearched with '" + StringUtils.stripToEmpty(keyword) + "'");

		/*Search the product*/
		List<Product> matchedProducts = productService.findByNameContains(keyword);

		/* Generate the response */
		if (!matchedProducts.isEmpty()) {
			jsonMap.put("status", "found");
			jsonMap.put("products", matchedProducts);
		} else {
			jsonMap.put("status", "not found");
			jsonMap.put("text", infoText);
		}

		return jsonMap;
	}

	@RequestMapping(value = "/product/search_old", 
					method = RequestMethod.GET)
	public void productSearch_old(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String keyword = StringUtils.stripToEmpty(request.getParameter("q"));
		String infoText = String.format("Could not find any product matches '%s'", keyword);

		logger.info("productSearched with '" + StringUtils.stripToEmpty(keyword) + "'");
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		/* Search the products */
		/*
		 * List<String> matchedProducts = new ArrayList<String>(); for(String
		 * product : products) { if(product.contains(keyword))
		 * matchedProducts.add(product); }
		 */
		List<Product> matchedProducts = productService.findByNameContains(keyword);

		/* Generate the response */
		StringBuffer jsonBuffer = new StringBuffer();
		if (!matchedProducts.isEmpty()) {
			jsonBuffer.append("{\"status\":\"found\", \"products\":[");

			for (Product product : matchedProducts) {
				String productJson = String.format("{\"name\": \"%s\", \"description\": \"%s\"}", product.getName(),
						product.getDescription());
				jsonBuffer.append(productJson + " ,");
			}
			jsonBuffer.append("]}");
		} else {
			jsonBuffer.append("{\"status\":\"not found\", \"text\":\"" + infoText + "\"}");
		}

		response.getWriter().write(jsonBuffer.toString());
	}
}
