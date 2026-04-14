package com.careerit.dto;

public class ForgotPassword {
	private String email;

	public ForgotPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForgotPassword(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ForgotPassword [email=" + email + "]";
	}
	
}
