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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.model.Category;
import com.quiz.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	
	
	/* add category	*/
	@PostMapping("/addCategory")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category category1 = categoryService.addCategory(category);
		return ResponseEntity.ok(category1);
	}

	
	
	
	/* update category	*/
	@PutMapping("/updateCategory")
	public Category updateCategory(@RequestBody Category category) {
		return this.categoryService.updateCategory(category);
	}

	
	
	
	/*  get single category	*/
	@GetMapping("/viewCategory/{catId}")
	public Category getCategory(@PathVariable("catId") Long catId) {
		return this.categoryService.getCategory(catId);
	}

	
	
	
	/* get all categories  */
	@GetMapping("/viewAllCategories")
	public ResponseEntity<?> getCategories() {
		return ResponseEntity.ok(this.categoryService.getCategories());
	}

	
	
	
	/* delete category	*/
	@DeleteMapping("/deleteCategory/{catId}")
	public ResponseEntity<String> deleteCategory(@PathVariable("catId") Long catId) {
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<>("Deleted Successfully !!!", HttpStatus.OK);
	}

}
