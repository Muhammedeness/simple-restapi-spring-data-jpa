package com.enesselvi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.enesselvi.StudentException.CustomNotFoundException;
import com.enesselvi.StudentException.CustomUserInDatabaseException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	//studentService de getAllStudents methodunun fırlattığı hatayı yakalayan handle methodu 
	@ExceptionHandler(CustomNotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleCustomNotFoundException (CustomNotFoundException e){
		return e.getMessage();
	}

	@ExceptionHandler(CustomUserInDatabaseException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public String handleUserInDatabaseException(CustomUserInDatabaseException e) {
		return e.getMessage();
	}
	
	
}
