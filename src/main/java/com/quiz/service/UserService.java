package com.quiz.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.quiz.model.User;
import com.quiz.model.UserRole;

public interface UserService {

	/*	creating user	*/
	public ResponseEntity<String> createUser(User user, Set<UserRole> userRoles);
	
	
	/*	updating user	*/
	public User updateUser(User user);
	
	
	/*	get user by username	*/
	public User getUser(String username) throws Exception;
	
	
	/*	delete user by id	*/
	public void deleteUser(Long userId);
	

	public List<User> viewAllusers();
	

	
	public User getUserDetails(String username) throws Exception;
	
}


