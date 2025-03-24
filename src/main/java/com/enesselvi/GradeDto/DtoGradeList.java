package com.enesselvi.GradeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoGradeList {
	private Double midTermGrade;
	

	private Double finalGrade;
	

	private Double makeupGrade;
	
	private String firstName;
	
	private String lastName;
}
