package com.quiz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.quiz.serviceImpl.UserDetailsServiceImpl;



@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
/*	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}		*/
	
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}





	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
				.csrf()
				.disable()
				.cors()
				.disable()
				.authorizeRequests()
				.antMatchers("/user/signup", "/user/login").permitAll()
				
				.antMatchers("/user/viewUser/{username}", "/user/viewAllUsers", "/user/deleteUser/{userId}", "/category/addCategory", "/category/updateCategory", "/category/deleteCategory/{catId}", "/category/quiz/addQuestion", "/category/quiz/updateQuestion", "/category/quiz/allQuiz/{quizId}", "/category/quiz/deleteQuestion/{quesId}", "/category/addQuiz", "/category/updateQuiz", "/category/deleteQuiz/{quizId}").hasAnyAuthority("ADMIN")
				
				.antMatchers("/user/updateUser", "/user/viewOwnself/{username}", "/category/viewAllCategories", "/category/viewCategory/{catId}", "/category/quiz/viewAllQuestions", "/category/quiz/viewQuestion/{quesId}", "/category/viewAllQuizzes", "/category/{catId}/viewQuiz/{quizId}", "/category/quiz/question/submit/{quesId}/{answer}").hasAnyAuthority("USER");

				
	}
		
	
}
