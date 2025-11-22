package com.quiz.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.quiz.model.Question;
import com.quiz.model.Quiz;

public interface QuestionService {

	public ResponseEntity<String> addQuestion(Question question) throws Exception;
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getQuestions();
	
	public Question getQuestion(Long quesId);
	
	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
	
	public void deleteQuestion(Long quesId);
	
	public Question get(Long quesId);

	public ResponseEntity<String> checkAnswer(String givenAnswer, long quesId);
	
}
