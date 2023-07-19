package net.javaguide.sprinboot.miceroservices.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleReosurceNotFound(ResourseNotFoundException exception,
			     WebRequest webrequest){
	ErrorDetails errorDetails = new ErrorDetails(
	LocalDateTime.now(),
	exception.getMessage(),
	webrequest.getDescription(false),
	"USER_NOT_FOUND"
			);
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<ErrorDetails> EmailAlreadyExistException(EmailAlreadyExistException exception,
			     WebRequest webrequest){
	ErrorDetails errorDetails = new ErrorDetails(
	LocalDateTime.now(),
	exception.getMessage(),
	webrequest.getDescription(false),
	"USER_EMAIL_ALREADY_EXIST"
			);
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	//Handle Global Exception apart from these two Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> hadleGlobalException(Exception exception,
			     WebRequest webrequest){
	ErrorDetails errorDetails = new ErrorDetails(
	LocalDateTime.now(),
	exception.getMessage(),
	webrequest.getDescription(false),
	"INTERNAL_SERCVER_ERROR"
			);
		return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		 Map<String,String> errors = new HashMap<>();
		 List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
		 errorList.forEach((error) ->{ 
			 String feildName = ((FieldError) error).getField();
			 String message = error.getDefaultMessage();
			 errors.put(feildName, message);
		 });
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
//How to custamize validation error message
}
