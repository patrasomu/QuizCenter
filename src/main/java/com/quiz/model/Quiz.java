package com.quiz.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="quiz")
public class Quiz {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long quizId;
	
	
	@NotBlank(message="Title can not be empty!!")
	@Size(min = 3, max = 200, message = "Title must be between 3-12 characters !!")
	private String title;
	
	
	
	@NotBlank(message = "Description can not be empty !!")
	@Size(min = 3, max = 500, message = "Description must be between 3-300 characters !!")
	private String description;
	
	
	
	@NotBlank(message = "Maximum Marks can not be empty !!")
	private String maxMarks;
	
	
	
	@NotBlank(message = "Number Of Questions can not be empty !!")
	private String numberOfQuestions;

	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	
	
	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Question> questions=new HashSet<>();
	
	
	
	public Quiz() {
		super();
	}

	


	public Long getQuizId() {
		return quizId;
	}



	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getMaxMarks() {
		return maxMarks;
	}



	public void setMaxMakrks(String maxMarks) {
		this.maxMarks = maxMarks;
	}



	public String getNumberOfQuestions() {
		return numberOfQuestions;
	}



	public void setNumberOfQuestions(String numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Set<Question> getQuestions() {
		return questions;
	}



	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	

	
}
