package net.javaguide.sprinboot.miceroservices.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourseNotFoundException extends RuntimeException{
	 private String resourceName;
	 private String feildName;
	 private Long fieldValue;
	 
	 //we create one parametrized constructor
	public ResourseNotFoundException(String resourceName, String feildName, Long fieldValue) {
		super(String.format("%s not found with %s:'%s'",resourceName,feildName,fieldValue));
		this.resourceName = resourceName;
		this.feildName = feildName;
		this.fieldValue = fieldValue;
	}
	 
	 
	
}
