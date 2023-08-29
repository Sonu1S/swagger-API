package net.javaguide.springboot.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDto {
   
	private String usernameOrEmail;
	private String password;
	
	
	public LoginDto() {
		super();
	}
	public LoginDto(String usernameOrEmail, String password) {
		super();
		this.usernameOrEmail = usernameOrEmail;
		this.password = password;
	}
	  
	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}
	public void setUsernameEmail(String usernameEmail) {
		this.usernameOrEmail = usernameEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
