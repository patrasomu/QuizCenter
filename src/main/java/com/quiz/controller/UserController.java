package com.quiz.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.model.Role;
import com.quiz.model.User;
import com.quiz.model.UserRole;
import com.quiz.service.UserService;

@RequestMapping("/user")
@RestController
@Validated
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	
	
	
	/* create user	*/
	@PostMapping("/signup")
	public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
		
		/*  Encoding password with BrycptPasswordEncoder    */
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		
		Set<UserRole> setRoles=new HashSet<>();
		
		Role role=new Role();
		role.setRoleId(2L);
		role.setRoleName("USER");
		
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		setRoles.add(userRole);
		
		return this.userService.createUser(user, setRoles);
		
	}

	
	/*  Login User	*/
	@PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody User user){
				
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()));
        System.out.println(authentication.isAuthenticated() +"   "+authentication.getPrincipal() + " "+ authentication.getDetails());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("******context*******"+SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
	
	
	
	/* Update User Details by Both  */
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		return ResponseEntity.ok(this.userService.updateUser(user));
	}
	
	
	
	/*  View All Users Details by Admin	 */
	@GetMapping("/viewUser/{username}")
	public User getUser(@PathVariable("username") String username) throws Exception {
		return this.userService.getUser(username);
	}
	
	
	
	
	/*	delete user by userId	*/
	@DeleteMapping("/deleteUser/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		this.userService.deleteUser(userId);
	}
	
	
	
	/* View All Users by Admin	*/
	@GetMapping("/viewAllUsers")
	public List<User> viewAllUsers(){
		return this.userService.viewAllusers();
	}
	
	
	
	/* View Ownself by User	*/
	@GetMapping("/viewOwnself/{username}")
	public User getUserDetails(@PathVariable("username") String username) throws Exception{
		return this.userService.getUserDetails(username);
	}

    /* Logout User by providing Username & Password */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuth == null || !currentAuth.isAuthenticated()) {
            return new ResponseEntity<>("No active session.", HttpStatus.UNAUTHORIZED);
        }

        String currentUsername = currentAuth.getName();
        if (!currentUsername.equals(user.getUsername())) {
            return new ResponseEntity<>("Username does not match authenticated user.", HttpStatus.FORBIDDEN);
        }

        try {
            User stored = this.userService.getUser(user.getUsername());
            if (stored == null || !bCryptPasswordEncoder.matches(user.getPassword(), stored.getPassword())) {
                return new ResponseEntity<>("Invalid credentials.", HttpStatus.UNAUTHORIZED);
            }

            new SecurityContextLogoutHandler().logout(request, response, currentAuth);
            SecurityContextHolder.clearContext();
            return new ResponseEntity<>("User signed-out successfully!.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error during logout.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
