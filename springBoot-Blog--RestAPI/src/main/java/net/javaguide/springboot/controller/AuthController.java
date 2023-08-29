package net.javaguide.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguide.springboot.payload.LoginDto;
import net.javaguide.springboot.payload.SignUpDto;
import net.javaguide.springboot.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	//build login api
    @PostMapping({"/login","/signin"})
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
		String response = authService.login(loginDto);
		return ResponseEntity.ok(response);
		
	}
    
    //build Signup api or register api
    
    @PostMapping({"/register","/signup"}) //value attribute
    public ResponseEntity<String> register(@RequestBody SignUpDto signUpDto){
		String response = authService.signUp(signUpDto);
		
    	return new ResponseEntity<>(response,HttpStatus.CREATED);
    	
    }
}
