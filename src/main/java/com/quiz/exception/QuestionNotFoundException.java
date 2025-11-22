package com.quiz.exception;

public class QuestionNotFoundException extends Exception {
	String msg;

	public QuestionNotFoundException(String msg) {
		super(msg);
	}

}
