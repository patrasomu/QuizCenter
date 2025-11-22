package com.quiz.exception;

public class QuizNotFoundException extends Exception{

	String msg;
	
	public QuizNotFoundException(String msg) {
		super(msg);
	}
}
