package com.enesselvi.controller.impl;

import java.net.http.HttpResponse.ResponseInfo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enesselvi.GradeDto.DtoGradeAdd;
import com.enesselvi.controller.IGradeController;
import com.enesselvi.entites.GradeResponseDTO;
import com.enesselvi.entites.grades;
import com.enesselvi.services.IGradeCalculaterService;
import com.enesselvi.services.impl.GradeService;


@RestController
@RequestMapping("/rest/api/grade")
public class GradeController implements IGradeController{

	@Autowired
	GradeService gradeService ;
	
	@PostMapping(path = "/save-grade/{id}")
	@Override
	public ResponseEntity<?> saveGrade(@PathVariable (name = "id") Integer id,@RequestBody DtoGradeAdd dtoGradeAdd) {
		
		ResponseEntity<?> savedGrade = gradeService.saveGrade(id, dtoGradeAdd);
		
		return savedGrade;  
	}
	
	@GetMapping(path = "list-allgrades")
	@Override
	public ResponseEntity<?> listAllGrades() {
		
		return gradeService.listAllGrades();
	}

	@GetMapping(path = "get-avg/{id}")
	@Override
	public List<GradeResponseDTO> getGradesOfStudentASList(@PathVariable(name = "id")Integer id) {
		
		return gradeService.getGradesOfStudentASList(id);
	}

	
}
