package com.careerit.service;

import com.careerit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.careerit.dto.LoginResponse;
import com.careerit.entity.User;
import com.careerit.repository.UserRepository;

@Service
public class AuthService {
	
	private final JwtUtil jwtUtil;

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder encoder;

	AuthService(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	public String register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repo.save(user);
		return "User Registered";
	}
	
	public LoginResponse login(String email,String password) {
		User user =  repo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		if(!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Wrong password");
		}
		String token =  jwtUtil.generateToken(email);
		
		return new LoginResponse("success",token,email);
	}

}



