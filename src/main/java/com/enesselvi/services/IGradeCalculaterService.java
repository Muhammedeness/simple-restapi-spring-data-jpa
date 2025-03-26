package com.enesselvi.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.enesselvi.GradeDto.DtoGradeResponse;
import com.enesselvi.entites.Grade;

public interface IGradeCalculaterService {

	
	public List<DtoGradeResponse> calculateStudentAverageGrade(List<Grade> gradesList) ;
	
	
	public String calculateGradeCode(Double avg);
	
}
