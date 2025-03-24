package com.enesselvi.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.enesselvi.GradeDto.GradeResponseDTO;
import com.enesselvi.entites.Grade;

public interface IGradeCalculaterService {

	
	public ResponseEntity<?> calculateStudentAverageGrade(List<Grade> gradesList) ;
	
	
	public String calculateGradeCode(Double avg);
	
}
