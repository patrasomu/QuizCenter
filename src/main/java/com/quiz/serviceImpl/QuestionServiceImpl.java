package com.quiz.serviceImpl;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.exception.QuestionContentAlreadyPresentException;
import com.quiz.exception.QuestionNotFoundException;
import com.quiz.model.Question;
import com.quiz.model.Quiz;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	
	@Autowired
	private QuestionRepository questionRepository;
	
	
	
	
	@Override
	public ResponseEntity<String> addQuestion(Question question) throws Exception {
		try {
		Question content=this.questionRepository.findByContent(question.getContent());
		if(content!=null) {
			throw new QuestionContentAlreadyPresentException("Content Already Exist!!");
		}
		else {
			this.questionRepository.save(question);
			return ResponseEntity.ok("Question Added");
			
		}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ResponseEntity.ok("Content Already Exist!!");
	}

	
	
	
	
	@Override
	public Question updateQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	
	
	
	@Override
	public Set<Question> getQuestions() {
		return new LinkedHashSet<>(questionRepository.findAll());
	}

	
	
	
	@Override
	public Question getQuestion(Long quesId) {
		return this.questionRepository.findById(quesId).get();
	}

	
	
	
	@Override
	public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
		return this.questionRepository.findByQuiz(quiz);
	}
	
	
	
	
	@Override
	public void deleteQuestion(Long quesId) {
/*		Question question=new Question();
		question.setQuesId(quesId);			*/
		this.questionRepository.deleteById(quesId);
		
	}

	
	
	
	@Override
	public Question get(Long quesId) {		
		return this.questionRepository.getOne(quesId);
	}

	
	
	
	@Override
	public ResponseEntity<String> checkAnswer(String givenAnswer, long quesId) {
		
		Optional<Question> question=questionRepository.findById(quesId);
		System.out.println(question.get().getAnswer());
		try {
		    if(question.isPresent()) {
		    	if(givenAnswer.equalsIgnoreCase(question.get().getAnswer())) {
		    		return ResponseEntity.ok("Correct Answer");
		    	}
		       
		    	else {
		    		return ResponseEntity.ok("Incorrect Answer");
		    	}
		    }
		    else{
		        throw new QuestionNotFoundException("Question doesn't exists");

		    }

		}catch(Exception e) {

		    return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}


}
