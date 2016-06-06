package com.mycompany.ppms.exception;

import java.io.Serializable;

import org.hibernate.ObjectNotFoundException;

public class EntityObjectNotFoundException extends ObjectNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityObjectNotFoundException(Serializable identifier, String clazz) {
		super(identifier, clazz);
		// TODO Auto-generated constructor stub
	}

}
