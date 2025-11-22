package com.quiz.service;

import java.util.Set;

import com.quiz.model.Quiz;

public interface QuizService {

	public Quiz addQuiz(Quiz quiz) throws Exception;
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizzes();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);
	
}
