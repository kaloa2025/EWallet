package com.aalok.UserServiceApplication;

import com.aalok.UserServiceApplication.Repo.UserRepo;
import com.aalok.UserServiceApplication.model.UserType;
import com.aalok.UserServiceApplication.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Value("${service.Authority}")
	private String serviceAuthority;
	@Autowired
	private UserRepo userRepo;
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Users users=  Users.builder().contact("txn-service").
				password(passwordEncoder.encode("txn-service")).
				isEnabled(true).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).
				email("txnService@gmail.com").authorities(serviceAuthority).
				userType(UserType.SERVICE).
				build();
		userRepo.save(users);
	}
}
