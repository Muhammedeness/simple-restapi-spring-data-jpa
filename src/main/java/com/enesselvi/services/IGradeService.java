package com.enesselvi.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.enesselvi.GradeDto.DtoGradeAdd;
import com.enesselvi.entites.GradeResponseDTO;

public interface IGradeService {

	public ResponseEntity<?> saveGrade( Integer id , DtoGradeAdd dtoGradeAdd);
	
	public ResponseEntity<?> listAllGrades();
	
	public List<GradeResponseDTO> getGradesOfStudentASList(Integer id);
	
	
	
}
