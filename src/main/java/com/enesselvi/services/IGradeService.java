package com.enesselvi.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.enesselvi.GradeDto.DtoGradeAdd;
import com.enesselvi.GradeDto.GradeResponseDTO;

public interface IGradeService {

	public ResponseEntity<?> saveGrade( Integer number , DtoGradeAdd dtoGradeAdd);
	
	public ResponseEntity<?> listAllGrades();
	
	public ResponseEntity<?> getGradesOfStudentASList(Integer id);
	
	
	
}
