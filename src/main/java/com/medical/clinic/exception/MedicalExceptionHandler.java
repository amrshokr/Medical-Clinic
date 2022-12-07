package com.medical.clinic.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MedicalExceptionHandler {

	@ExceptionHandler(value = {MedicalException.class})
	public ResponseEntity<Object> handleDroneException(MedicalException e){
		
		HttpStatus httpStatus=HttpStatus.BAD_REQUEST;
		
		Exception exception = new Exception(
				e.getMessage(),
				httpStatus,
				ZonedDateTime.now()
				); 
		return new ResponseEntity<Object>(exception,httpStatus);
	}
}
