package net.javaguide.sprinboot.miceroservices.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import net.javaguide.sprinboot.miceroservices.dto.UserDto;
import net.javaguide.sprinboot.miceroservices.entity.User;
import net.javaguide.sprinboot.miceroservices.exception.ErrorDetails;
import net.javaguide.sprinboot.miceroservices.exception.ResourseNotFoundException;
import net.javaguide.sprinboot.miceroservices.service.UserService;

@Tag(
		name = "Crud Rest API for users Resource",
		description ="CRUD REST API - Create User,updateUser, Get User,Get All users, Delete User and Delete ALL Users"
		
		)



@RestController
@RequestMapping("/api/users")
public class UserController {
     
	UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@Operation( //its for custamize documentaion
			  summary = "Create User Rest API",
			  description = "Create User Rest API is Used to save user in a database"
		)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 CREATED"
		)
	
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		UserDto savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser,HttpStatus.CREATED) ;
	}
	
	@Operation( //its for custamize documentaion
			  summary = "Get User By Id Rest API",
			  description = "Get User By Id Rest API is Used to get a single user from the database"
		)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
		)
	
	//get user id rest api
	@GetMapping("/{id}") // http://localhost:9090/api/users/3
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
		UserDto user = userService.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@Operation( //its for custamize documentaion
			  summary = "Get All User Details Rest API",
			  description = "Get All User Details Rest API is Used to get All User from the database"
		)
	@ApiResponse(
				responseCode = "200",
				description = "HTTP Status 200 SUCCESS"
		)

	//build get all users rest api
	
	@GetMapping //  http://localhost:9090/api/users
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
	
	@Operation( //its for custamize documentaion
			  summary = "Updatd User Details Rest API",
			  description = "Update User Details Rest API is Used to Update particular User in the database"
		)
	@ApiResponse(
				responseCode = "200",
				description = "HTTP Status 200 SUCCESS"
		)
	
	//Build updated User REST API
	@PutMapping("{id}") // http://localhost:9090/api/users/1
	public ResponseEntity<UserDto>updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user){
		user.setId(userId);
		UserDto updateUser = userService.updateUser(user);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
	}
	
	@Operation( //its for custamize documentaion
			  summary = "Delete User By Id Rest API",
			  description = "Delete User Details Rest API is Used to Delete User By id in the database"
		)
	@ApiResponse(
				responseCode = "200",
				description = "HTTP Status 200 SUCCESS"
		)
	
	//Build delete user rest api
	@DeleteMapping("{id}")//http://localhost:9090/api/users/1
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
		userService.deleteUser(userId);
		return new ResponseEntity<>("User Successfully deleted With! " + userId, HttpStatus.OK) ;
	}
	
	@Operation( //its for custamize documentaion
			  summary = "Delete All User Rest API",
			  description = "Delete All User Rest API is Used to Delete All User From The Database"
		)
	@ApiResponse(
				responseCode = "200",
				description = "HTTP Status 200 SUCCESS"
		)
	
	//Build delete all user rest api
	@DeleteMapping //http://localhost:9090/api/users
	public ResponseEntity<String> deleteAllUser(){
		userService.deleteAllUser();
		return new ResponseEntity<String>("deleted successfully ! :",HttpStatus.OK);
		
	}
	
//Write Logic to hadle custome exception in controller layer
	
//	@ExceptionHandler(ResourseNotFoundException.class)
//	public ResponseEntity<ErrorDetails> handleReosurceNotFound(ResourseNotFoundException exception,
//			     WebRequest webrequest){
//	ErrorDetails errorDetails = new ErrorDetails(
//	LocalDateTime.now(),
//	exception.getMessage(),
//	webrequest.getDescription(false),
//	"USER_NOT_FOUND"
//			);
//		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
//		
//	}
}
