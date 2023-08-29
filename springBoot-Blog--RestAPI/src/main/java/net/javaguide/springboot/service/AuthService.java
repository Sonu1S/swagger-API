package net.javaguide.springboot.service;

import net.javaguide.springboot.payload.LoginDto;
import net.javaguide.springboot.payload.SignUpDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    
    String signUp(SignUpDto signUpDto);
}
