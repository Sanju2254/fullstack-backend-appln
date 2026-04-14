package com.careerit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.careerit.dto.ForgotPassword;
import com.careerit.dto.LoginRequest;
import com.careerit.dto.LoginResponse;
import com.careerit.dto.RegisterResponse;
import com.careerit.dto.ResetPassword;
import com.careerit.entity.User;
import com.careerit.service.AuthService;

@RestController
@CrossOrigin
public class AuthController {
	@Autowired
	private AuthService service;
	
	
	@GetMapping("/")
	public String wish() {
		return "Hello";
	}
	
	@PostMapping("/register")
	public RegisterResponse register(@RequestBody User user) {
		return service.register(user);
	}
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest req) {
		return service.login(req.getEmail(),req.getPassword());
	}
	
	@PostMapping("/forgot-password")
	public ForgotPassword forgotPassword(@RequestBody LoginRequest request) {
		return service.forgotPassword(request.getEmail());
	}
	
	@PostMapping("/reset-password")
	public ResetPassword resetPassword(@RequestParam String token,@RequestBody LoginRequest req) {
		return service.resetPassword(token, req.getPassword());
	}
}
