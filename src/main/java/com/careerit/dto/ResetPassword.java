package com.careerit.dto;

public class ResetPassword {
	private String message;

	public ResetPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResetPassword(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResetPassword [message=" + message + "]";
	}
	
}
