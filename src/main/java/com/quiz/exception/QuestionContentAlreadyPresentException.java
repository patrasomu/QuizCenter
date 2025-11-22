package com.quiz.exception;

public class QuestionContentAlreadyPresentException extends Exception{

	String msg;

	public QuestionContentAlreadyPresentException(String msg) {
		super(msg);
	}
	
	
}
