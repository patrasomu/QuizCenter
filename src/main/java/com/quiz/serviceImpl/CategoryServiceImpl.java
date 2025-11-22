package com.quiz.serviceImpl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.model.Category;
import com.quiz.repository.CategoryRepository;
import com.quiz.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	@Override
	public Category addCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	
	
	@Override
	public Category updateCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	
	
	@Override
	public Set<Category> getCategories() {
		
		return new LinkedHashSet<>(categoryRepository.findAll());
	}

	
	
	@Override
	public Category getCategory(Long catId) {
		
		return this.categoryRepository.findById(catId).get();
	}

	
	
	@Override
	public void deleteCategory(Long catId) {
/*		Category category=new Category();
		category.setCatId(catId);		*/
		this.categoryRepository.deleteById(catId);
		
	}

}
