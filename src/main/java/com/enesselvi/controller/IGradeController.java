package com.enesselvi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.enesselvi.GradeDto.DtoGradeAdd;
import com.enesselvi.entites.GradeResponseDTO;
import com.enesselvi.entites.grades;

public interface IGradeController {

	
	public ResponseEntity<?> saveGrade(Integer id , DtoGradeAdd dtoGradeAdd);
		
	public ResponseEntity<?> listAllGrades();
	
	public List<GradeResponseDTO> getGradesOfStudentASList(Integer id) ;
	
	
}
