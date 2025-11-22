package com.quiz.exception;

public class UserNotFoundException extends Exception {
	
	String msg;
	
	public UserNotFoundException(String msg) {
		super(msg);
	}

}
