package com.careerit.dto;

public class RegisterResponse {
	private String register;

	public RegisterResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisterResponse(String register) {
		super();
		this.register = register;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	@Override
	public String toString() {
		return "RegisterResponse [register=" + register + "]";
	}
	
}
