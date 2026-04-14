package com.careerit.dto;



public class LoginResponse {
	private String login;
	private String token;
	private String email;
	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResponse(String login, String token, String email) {
		super();
		this.login = login;
		this.token = token;
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "LoginResponse [login=" + login + ", token=" + token + ", email=" + email + "]";
	}
	
	
}
