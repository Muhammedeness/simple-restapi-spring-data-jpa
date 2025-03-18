package com.enesselvi.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enesselvi.entites.Student;
import com.enesselvi.entites.grades;
import com.enesselvi.repository.GradesRepository;
import com.enesselvi.services.IGradeService;


@Service
public class GradeService  implements IGradeService{
	
	
	
	@Autowired
	GradesRepository gradesRepository ;
	
	@Autowired
	StudentService studentService;	
	
	@Override
	public grades saveGrade(Integer id  , grades grade) {

		Student student = studentService.getStudentById(id);
		
		if (student!= null) {
			grade.setStudent(student);
			
			return gradesRepository.save(grade);
		}
	  return null;
		
	}

	@Override
	public grades getGradesByStudentId(Integer id) {
		
		Optional<grades> optional = gradesRepository.findById(id);
		
		 if (optional.isPresent()){

			 return optional.get();
		}
		return null;
	}

	@Override
	public List<grades> listAllGrades() {
		
		List<grades> gradesList = gradesRepository.findAll();
		
		return gradesList;
	}

}
