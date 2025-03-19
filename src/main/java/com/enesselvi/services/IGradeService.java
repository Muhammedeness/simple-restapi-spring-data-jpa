package com.enesselvi.services;

import java.util.List;

import com.enesselvi.entites.GradeResponseDTO;
import com.enesselvi.entites.grades;

public interface IGradeService {

	public grades saveGrade( Integer id , grades grade);

	
	public grades getGradesByStudentId(Integer id);
	
	public List<grades> listAllGrades();
	
	public List<GradeResponseDTO> getGradesOfStudentASList(Integer id);
	
	
	
}
