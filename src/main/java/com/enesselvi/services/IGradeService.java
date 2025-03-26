package com.enesselvi.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.enesselvi.GradeDto.DtoGrade;
import com.enesselvi.GradeDto.DtoGradeAdd;
import com.enesselvi.GradeDto.DtoGradeList;
import com.enesselvi.GradeDto.DtoGradeResponse;

public interface IGradeService {

	public DtoGrade saveGrade( Integer number , DtoGradeAdd dtoGradeAdd);
	
	public List<DtoGradeList> listAllGrades();
	
	public List<DtoGradeResponse> getGradesOfStudentASList(Integer id);
	
	
	
}
