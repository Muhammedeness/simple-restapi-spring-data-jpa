package com.enesselvi.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enesselvi.controller.IGradeController;
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
	public grades saveGrade(@PathVariable (name = "id") Integer id,@RequestBody grades grade) {
		
		grades savedGrade = gradeService.saveGrade(id, grade);
		
		return savedGrade;  
	}

	@GetMapping(path = "list-grade/{id}")
	@Override
	public grades getGradesByStudentId(@PathVariable(name = "id") Integer id) {
		
		return  gradeService.getGradesByStudentId(id);
	}

	@GetMapping(path = "list-allgrades")
	@Override
	public List<grades> listAllGrades() {
		
		return gradeService.listAllGrades();
	}

	@GetMapping(path = "get-avg/{id}")
	@Override
	public String getGradesOfStudentASList(@PathVariable(name = "id")Integer id) {
		
		return gradeService.getGradesOfStudentASList(id);
	}

	
}
