package com.mycompany.ppms.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger log = LoggerFactory.getLogger(ProductNotFoundException.class);
	
	public ProductNotFoundException() {
		
	}
	
	public ProductNotFoundException(String productId) {
		log.error("Product not found with Product Id : " + productId);
	}
}
