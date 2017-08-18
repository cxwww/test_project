package com.example.demo.service;

import org.springframework.dao.DataAccessException;

public class TestTransactionalException extends DataAccessException{
	
	private static final long serialVersionUID = 1L;

	public TestTransactionalException(String msg) {
		super(msg);
	}
	
}
