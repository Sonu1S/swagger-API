package net.javaguide.springboot.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import net.javaguide.springboot.payload.ErrorDetails;

@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
public class GlobalExceptionHandler { 
	
	//handle specific exception 
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
			ResourceNotFoundException exception, WebRequest webRequest){
	
		ErrorDetails errorDetails = new ErrorDetails(new Date(), 
			exception.getMessage(),webRequest.getDescription(false));
		
	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(BlogAPIException.class)
	public ResponseEntity<ErrorDetails> handleBlogAPIException(
			BlogAPIException exception, WebRequest webRequest){
	ErrorDetails errorDetails = new ErrorDetails(new Date(), 
			exception.getMessage(),webRequest.getDescription(false));
		
	return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
		
	}
	//global exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){
	ErrorDetails errorDetails = new ErrorDetails(new Date(), 
			exception.getMessage(),webRequest.getDescription(false));
		
	return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
//	@Override  //this is the first approch
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//				HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//
//			 Map<String,String> errors = new HashMap<>();
//			 List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
//			 errorList.forEach((error) ->{ 
//				 String feildName = ((FieldError) error).getField();
//				 String message = error.getDefaultMessage();
//				 errors.put(feildName, message);
//			 });
//			return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
//		}
//	
	//this is the Secound approch
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			                                                         WebRequest webRequest){
		 Map<String,String> errors = new HashMap<>();
		 exception.getBindingResult().getAllErrors().forEach((error) ->{ 
			 String feildName = ((FieldError) error).getField();
			 String message = error.getDefaultMessage();
			 errors.put(feildName, message);
		 });
	return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorDetails> handleGlobalAccessDeniedException(AccessDeniedException exception, WebRequest webRequest){
	ErrorDetails errorDetails = new ErrorDetails(new Date(), 
			exception.getMessage(),webRequest.getDescription(false));
		
	return new ResponseEntity<>(errorDetails,HttpStatus.UNAUTHORIZED);
		
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ErrorDetails> handleBadCredentialsException(BadCredentialsException exception, WebRequest webRequest){
	ErrorDetails errorDetails = new ErrorDetails(new Date(), 
			exception.getMessage(),webRequest.getDescription(false));
		
	return new ResponseEntity<>(errorDetails,HttpStatus.UNAUTHORIZED);
		
	}
}
