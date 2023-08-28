package com.jdbcproject.exceptions;

public class DuplicatePhoneNumberException extends Exception {
	public DuplicatePhoneNumberException(String errorMsg) {
		super(errorMsg);  
	  }
}
