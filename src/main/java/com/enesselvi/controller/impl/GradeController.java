package com.enesselvi.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enesselvi.GradeDto.DtoGrade;
import com.enesselvi.GradeDto.DtoGradeAdd;
import com.enesselvi.GradeDto.DtoGradeList;
import com.enesselvi.GradeDto.DtoGradeResponse;
import com.enesselvi.controller.IGradeController;
import com.enesselvi.services.impl.GradeService;


@RestController
@RequestMapping("/rest/api/grade")
public class GradeController implements IGradeController{

	@Autowired
	GradeService gradeService ;
	
	@PostMapping(path = "/save-grade/{num}")
	@Override
	public DtoGrade saveGrade(@PathVariable (name = "num") Integer num,@RequestBody DtoGradeAdd dtoGradeAdd) {
		
	    DtoGrade savedGrade = gradeService.saveGrade(num, dtoGradeAdd);
		
		return savedGrade;  
	}
	
	@GetMapping(path = "list-allgrades")
	@Override
	public List<DtoGradeList> listAllGrades() {
		
		return gradeService.listAllGrades();
	}

	@GetMapping(path = "get-avg/{id}")
	@Override
	public List<DtoGradeResponse> getGradesOfStudentASList(@PathVariable(name = "id")Integer id) {
		
		return gradeService.getGradesOfStudentASList(id);
	}

	
}
