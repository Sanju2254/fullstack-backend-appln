package com.careerit.service;

import com.careerit.util.JwtUtil;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.careerit.dto.ForgotPassword;
import com.careerit.dto.LoginResponse;
import com.careerit.dto.RegisterResponse;
import com.careerit.dto.ResetPassword;
import com.careerit.entity.User;
import com.careerit.repository.UserRepository;

@Service
public class AuthService {
	
	private final JwtUtil jwtUtil;

	@Autowired
	private EmailService emailService;
	
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder encoder;

	AuthService(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	public RegisterResponse register(User user) {
	    user.setPassword(encoder.encode(user.getPassword()));
	    if (user.getRole() == null) {
	        user.setRole("USER");
	    }
	    repo.save(user);
	    return new RegisterResponse("success");
	}
	
	public LoginResponse login(String email,String password) {
		User user =  repo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
		if(!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Wrong password");
		}
		String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
		
		return new LoginResponse("success",token,email);
	}
	
	
	public ForgotPassword forgotPassword(String email) {
		User user =  repo.findByEmail(email).orElseThrow(()-> new RuntimeException("User Not Found"));
		
		String token = UUID.randomUUID().toString();
		user.setResetToken(token);
		repo.save(user);
		
		String resetLink = "http://localhost:9090/reset-password?token="+token;
		
		emailService.sendEmail(email, "Password Reset", "Click Here to reset your password "+resetLink);
		
		return new ForgotPassword("Reset Link Sent to email successfully !!!");
	}
	
	public ResetPassword resetPassword(String token,String newPassword) {
		
		User user = repo.findByResetToken(token).orElseThrow(()-> new RuntimeException("Invalid Token"));
		user.setPassword(encoder.encode(newPassword));
		user.setResetToken(null);
		repo.save(user);
		return new ResetPassword("Password Reset Successfully !!!");
	}

}


