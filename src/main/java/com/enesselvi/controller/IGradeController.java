package com.enesselvi.controller;

import java.util.List;

import com.enesselvi.entites.grades;

public interface IGradeController {

	
	public grades saveGrade(Integer id , grades grade);
	
	public grades getGradesByStudentId(Integer id);
	
	public List<grades> listAllGrades();
	
	public String getGradesOfStudentASList(Integer id) ;
	
	
}
