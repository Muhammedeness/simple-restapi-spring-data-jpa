package com.enesselvi.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoStudentSave {
	
		
	private String firstName;
	
	private String lastName;
	
	private String birthOfDate;
	
	private Integer stuNumber;
	
}
