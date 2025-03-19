package com.enesselvi.controller;

import java.util.List;

import com.enesselvi.entites.grades;

public interface IGradeCalculaterController {

	public Double calculateStudentAverageGrade(List<grades> gradesList);
	
}
