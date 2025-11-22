package com.quiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.model.Quiz;
import com.quiz.service.QuizService;

@RestController
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	
	
	/* add quiz	*/
	@PostMapping("/category/addQuiz")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) throws Exception {
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}
	
	
	
	/* update quiz	*/
	@PutMapping("/category/updateQuiz")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	
	
	
	/* get quizes	*/
	@GetMapping("/category/viewAllQuizzes")
	public ResponseEntity<?> getQuizzes(){
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}
	
	
	
	/* get single quiz	*/
	@GetMapping("/category/{catId}/viewQuiz/{quizId}")
	public Quiz getQuiz(@PathVariable("quizId") Long quizId) {
		return this.quizService.getQuiz(quizId);
	}
	
	
	
	/* delete quiz	*/
	@DeleteMapping("/category/deleteQuiz/{quizId}")
	public ResponseEntity<String> deleteQuiz(@PathVariable("quizId") Long quizid) {
		this.quizService.deleteQuiz(quizid);
		return new ResponseEntity<>("Deleted Successfully !!!", HttpStatus.OK);
		
	}
	
}
