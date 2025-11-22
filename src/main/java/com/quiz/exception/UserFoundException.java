package com.quiz.exception;

public class UserFoundException extends Exception{

	String msg;

	public UserFoundException(String msg) {
		super(msg);
	}

//	public UserFoundException() {
//		super("User with this username is already present in database !!");
//	}
//	
	
}
