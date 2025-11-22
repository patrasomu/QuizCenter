package com.quiz.serviceImpl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.exception.QuizAlreadyPresentException;
import com.quiz.model.Quiz;
import com.quiz.repository.QuizRepository;
import com.quiz.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	
	@Autowired
	private QuizRepository quizRepository;
	
	
	
	@Override
	public Quiz addQuiz(Quiz quiz) throws Exception {
		
		Quiz title=this.quizRepository.findByTitle(quiz.getTitle());
		if(title!=null) {
			throw new QuizAlreadyPresentException("Quiz Already Exist!!");
		}
		return this.quizRepository.save(quiz);
	}

	
	
	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return this.quizRepository.save(quiz);
	}

	
	
	@Override
	public Set<Quiz> getQuizzes() {
		return new LinkedHashSet<>(quizRepository.findAll());
	}

	
	
	@Override
	public Quiz getQuiz(Long quizId) {
		return this.quizRepository.findById(quizId).get();
	}

	
	
	@Override
	public void deleteQuiz(Long quizId) {
/*		Quiz quiz=new Quiz();
		quiz.setQuizId(quizId);		*/
		this.quizRepository.deleteById(quizId);
	}
	
	

}
