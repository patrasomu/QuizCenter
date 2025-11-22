package com.quiz.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.quiz.exception.UserFoundException;
import com.quiz.exception.UserNotFoundException;
import com.quiz.model.User;
import com.quiz.model.UserRole;
import com.quiz.repository.RoleRepository;
import com.quiz.repository.UserRepository;
import com.quiz.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	
	
	/*  Creating user    */
	@Override
	public ResponseEntity<String> createUser(User user, Set<UserRole> userRoles) {
		User local1= new User();
		try {
			local1=this.userRepository.findByUsername(user.getUsername());
			User local2=this.userRepository.findByEmail(user.getEmail());
			User local3=this.userRepository.findByPhone(user.getPhone());
		
			if(local1!=null) {
				throw new UserFoundException("User with this Username is already present in database !!");
			}
			else if(local2!=null) {
				throw new UserFoundException("User with this Email is already present in database !!");
			}
			else if(local3!=null) {
				throw new UserFoundException("User with this Phone Number is already present in database !!");
			}
			else {
				for(UserRole ur:userRoles) {
					roleRepository.save(ur.getRole());
				}
				user.getUserRoles().addAll(userRoles);
				local1=this.userRepository.save(user);
				System.out.println(local1);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(local1.equals(user)) {
			return new ResponseEntity<String>("Success....", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Failure....", HttpStatus.OK);
	}

	
	
	
	
	/*   updating user    */
	@Override
	public User updateUser(User user) {
		return this.userRepository.save(user);
	}
	
	
	
	
	
	
	/*	getting user by username	*/
	@Override
	public User getUser(String username) throws Exception {
		User user=this.userRepository.findByUsername(username);
		if(user==null) {
			throw new UserNotFoundException("User Not Exist !!");
		}
		
		return this.userRepository.findByUsername(username);
	}

	
	
	
	
	
	/*	delete user by userId	*/
	@Override
	public void deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
	}




	/*   View All Users by Admin	*/	
	@Override
	public List<User> viewAllusers() {
		List<User> users=userRepository.findAll();
		return users;
		
	}




	/*	 Getting User Details by User 	 */
	@Override
	public User getUserDetails(String username) throws Exception {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null && authentication.isAuthenticated()) {
			Object principal=authentication.getPrincipal();
			User user=(User)principal;
			String userName=user.getUsername();
			if(username.equals(userName)) {
				return this.userRepository.findByUsername(username);
			}
			else {
				try {
					throw new UserNotFoundException("User can't see other user details");
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
		}
		User local=this.userRepository.findByUsername(username);
		if(local==null) {
			throw new UserNotFoundException("User Not Exist !!");
		}
		return null;
	}


}
