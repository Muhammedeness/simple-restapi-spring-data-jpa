package com.enesselvi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.enesselvi.GradeDto.DtoGradeAdd;
import com.enesselvi.GradeDto.GradeResponseDTO;

public interface IGradeController {

	
	public ResponseEntity<?> saveGrade(Integer number , DtoGradeAdd dtoGradeAdd);
		
	public ResponseEntity<?> listAllGrades();
	
	public List<GradeResponseDTO> getGradesOfStudentASList(Integer id) ;
	
	
}
