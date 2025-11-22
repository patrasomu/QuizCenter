package com.quiz.exception;

public class QuizAlreadyPresentException extends Exception {

	String msg;
	public QuizAlreadyPresentException(String msg) {
		super(msg);
	}
}
