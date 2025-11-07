package com.spp.banking_app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	//Hanlde specific exception - AccountException
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<ErrorDetails> handleAccountException(AccountException exception, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
				webRequest.getDescription(false), "ACCOUNT_NOT_FOUND");

		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	//Hanlde Generic exception 
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleException(Exception exception, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
				webRequest.getDescription(false), "INTERNAL_SERVER_ERROR");

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	


}
