package com.enesselvi.Exception;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomMethodArgumentNotValidExceptionMessage {

	private Map<String, String> methodNotValidErrors = new HashMap<>();
	
	public void addErrorToTheMap(String field , String errorMessage) {
		
		methodNotValidErrors.put(field, errorMessage);
	}
	
	
	
}
