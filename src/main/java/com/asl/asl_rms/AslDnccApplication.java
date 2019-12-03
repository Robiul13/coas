package com.asl.asl_rms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.asl.asl_rms.model.FileStorageProperties;
import com.asl.asl_rms.model.User;
import com.asl.asl_rms.service.RoleService;
import com.asl.asl_rms.service.UserService;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
@Import(QartzConfig.class)
public class AslDnccApplication {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(AslDnccApplication.class, args);
	}
	

	@Bean
	public CommandLineRunner  run() {
		return (args) -> {
			//
			//public User( String password, String firstName, String lastName, String email, String mobileNumber, String address)
			User user = new User("ashid", "Ashid", "Khan", "ashid8bd@gmail.com", "12345678", "abc");
			//Role role = roleService.getOne(1l);
			//user.setRole(role);
			user.setActive(true);
			user.setUsername("ashid8bd@gmail.com");
			//userService.saveUser(user);
		};
	}
	
	

}
