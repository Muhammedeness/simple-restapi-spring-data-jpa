package com.enesselvi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.enesselvi.Exception.CustomAlreadyInDatabaseException;
import com.enesselvi.Exception.CustomNotFoundException;
import com.enesselvi.Exception.CustomNullException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	//studentService de getAllStudents methodunun fırlattığı hatayı yakalayan handle methodu 
	@ExceptionHandler(CustomNotFoundException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleCustomNotFoundException (CustomNotFoundException e){
		return e.getMessage();
	}

	@ExceptionHandler(CustomAlreadyInDatabaseException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public String handleUserInDatabaseException(CustomAlreadyInDatabaseException e) {
		return e.getMessage();
	}
	
	@ExceptionHandler(CustomNullException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleCustomNullException(CustomNullException e) {
		return e.getMessage();
	}
	
	
	
}
