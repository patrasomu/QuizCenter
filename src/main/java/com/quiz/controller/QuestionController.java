package com.quiz.controller;
import java.util.Set;

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

import com.quiz.model.Question;
import com.quiz.model.Quiz;
import com.quiz.service.QuestionService;

@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;

/*	@Autowired
	private QuizService quizService;	*/

	
	
	/* add question	*/
	@PostMapping("/category/quiz/addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) throws Exception{
		return this.questionService.addQuestion(question);
	}

	
	
	/* update question	*/
	@PutMapping("/category/quiz/updateQuestion")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}

	
	
	
	@GetMapping("/category/quiz/allQuiz/{quizId}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("quizId") Long quizId) {
		Quiz quiz = new Quiz();
		quiz.setQuizId(quizId);
		Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
		return ResponseEntity.ok(questionsOfQuiz);

	}

	
	
	/* get all question	*/
	@GetMapping("/category/quiz/viewAllQuestions")
	public ResponseEntity<?> getQuestions() {
		return ResponseEntity.ok(this.questionService.getQuestions());
	}

	
	
	/* get single question	*/
	@GetMapping("/category/quiz/viewQuestion/{quesId}")
	public Question getQuestion(@PathVariable("quesId") Long quesId) {
		return this.questionService.getQuestion(quesId);
	}

	
	
	
	/* delete question	*/
	@DeleteMapping("/category/quiz/deleteQuestion/{quesId}")
	public ResponseEntity<String> deleteQuestion(@PathVariable("quesId") Long quesId) {
		this.questionService.deleteQuestion(quesId);
		return new ResponseEntity<>("Deleted Successfully !!!", HttpStatus.OK);
	}

	
	
	
	@GetMapping("/category/quiz/question/submit/{quesId}/{givenAnswer}")
	public ResponseEntity<String> getAnswer(@PathVariable("givenAnswer") String givenAnswer, @PathVariable("quesId") long quesId){
		return this.questionService.checkAnswer(givenAnswer,quesId);
	}
	
	
}
