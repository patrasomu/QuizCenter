package com.quiz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizCenterApplication implements CommandLineRunner {

/*	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;		*/
	
	
	public static void main(String[] args) {
		SpringApplication.run(QuizCenterApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {

		System.out.println("START");
		
/*		User user=new User();
		user.setUsername("patrasomu");
		user.setPassword(this.bCryptPasswordEncoder.encode("Somnath@123"));
		user.setFirstName("Somnath");
		user.setLastName("Patra");
		user.setEmail("somu@gmail.com");
		user.setPhone("7001476932");
		
		Role role=new Role();
		role.setRoleId(1L);
		role.setRoleName("ADMIN");
		
		Set<UserRole>userRoleSet=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		userRoleSet.add(userRole);
		
		User user1=this.userService.createUser(user, userRoleSet);
		System.out.println(user1.getUsername());		*/
		
	}

}
