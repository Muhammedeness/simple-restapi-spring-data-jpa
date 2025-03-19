package com.enesselvi.services;

import java.util.List;

import com.enesselvi.entites.GradeResponseDTO;
import com.enesselvi.entites.grades;

public interface IGradeCalculaterService {

	
	public List<GradeResponseDTO> calculateStudentAverageGrade(List<grades> gradesList) ;
	
	
	public String calculateGradeCode(Double avg);
	
}
