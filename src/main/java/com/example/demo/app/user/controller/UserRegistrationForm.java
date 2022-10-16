package com.example.demo.app.user.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegistrationForm {
	
	@NotBlank(message="username is not blank")
	@Size(max= 20, message = "Please input 20chracters or less")
	private String name;
	
	@NotBlank(message = "email is not blank")
	@Email(message = "Invalid E-mail Format")
	private String email;
	
	@NotBlank(message = "login_id is not blank")
	@Size(min = 3, message = "Please input min 3characters")
	private String loginId;
	
	@Size(min = 4, message = "Please input min 4characters")
	private String password;
	
	@Size(min = 4, message = "Please input min 4characters")
	private String passwordConfirm;
	
	public UserRegistrationForm() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String password_confirm) {
		this.passwordConfirm = password_confirm;
	}
}
