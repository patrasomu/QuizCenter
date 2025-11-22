package com.quiz.service;

import java.util.Set;

import com.quiz.model.Category;

public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Set<Category> getCategories();
	
	public Category getCategory(Long catId);
	
	public void deleteCategory(Long catId);
	
}
