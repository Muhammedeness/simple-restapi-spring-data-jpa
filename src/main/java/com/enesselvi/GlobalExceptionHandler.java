package com.enesselvi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.enesselvi.Exception.CustomAlreadyInDatabaseException;
import com.enesselvi.Exception.CustomErrorException;
import com.enesselvi.Exception.CustomMethodArgumentNotValidExceptionMessage;
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
	
	@ExceptionHandler(CustomErrorException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleCustomErrorException(CustomErrorException e) {
		return e.getMessage();

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public CustomMethodArgumentNotValidExceptionMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		
		CustomMethodArgumentNotValidExceptionMessage customMethodArgumentNotValidExceptionMessage = new CustomMethodArgumentNotValidExceptionMessage();
		
		for (ObjectError objectError : e.getBindingResult().getAllErrors()) {
			
			String fieldname  = ((FieldError) objectError).getField();
			customMethodArgumentNotValidExceptionMessage.addErrorToTheMap(fieldname, objectError.getDefaultMessage());
		}
		return customMethodArgumentNotValidExceptionMessage;
	}

}
