package com.enesselvi.services;

import java.util.List;

import com.enesselvi.GradeDto.GradeResponseDTO;
import com.enesselvi.entites.Grade;

public interface IGradeCalculaterService {

	
	public List<GradeResponseDTO> calculateStudentAverageGrade(List<Grade> gradesList) ;
	
	
	public String calculateGradeCode(Double avg);
	
}
