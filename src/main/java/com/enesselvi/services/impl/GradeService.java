package com.enesselvi.services.impl;

import java.util.ArrayList;  
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.enesselvi.GradeDto.DtoGrade;
import com.enesselvi.GradeDto.DtoGradeAdd;
import com.enesselvi.GradeDto.DtoGradeList;
import com.enesselvi.GradeDto.GradeResponseDTO;
import com.enesselvi.entites.Student;
import com.enesselvi.entites.Grade;
import com.enesselvi.repository.GradesRepository;
import com.enesselvi.repository.StudentRepository;
import com.enesselvi.services.IGradeService;


@Service
public class GradeService  implements IGradeService{
	
	
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	GradesRepository gradesRepository ;

	
	
	@Autowired
	GradeCalculatorService gradeCalculatorService;
	
	@Override
	public ResponseEntity<?> saveGrade(Integer id  , DtoGradeAdd dtoGradeAdd) {

		Grade addGrade = new Grade();	
		DtoGrade dtoGrade = new DtoGrade();
		Optional<Student> optional = studentRepository.findById(id);
		if (optional.isPresent()) {
			 Student student = optional.get();
			 BeanUtils.copyProperties(dtoGradeAdd, addGrade);
			 
			 addGrade.setStudent(student);
			 Grade savedGrade = gradesRepository.save(addGrade);
			 BeanUtils.copyProperties(savedGrade, dtoGrade);

			return ResponseEntity.ok(dtoGrade);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Kullanıcı Bulunamadıı");
	}



	@Override
	public ResponseEntity<?> listAllGrades() {
		
		Student student = new Student();
		List<DtoGradeList> dtoGradesList= new ArrayList<>();
		List<Grade> gradesList = gradesRepository.findAll();
		if (!gradesList.isEmpty()) {

			for (Grade grade : gradesList) {
				DtoGradeList dtoGrade = new DtoGradeList();
				BeanUtils.copyProperties(grade, dtoGrade);
				
				student=grade.getStudent();
				dtoGrade.setFirstName(student.getFirstName());
				dtoGrade.setLastName(student.getLastName());
				
				dtoGradesList.add(dtoGrade);
			}
			return ResponseEntity.ok(dtoGradesList);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Not Listesi Boş.");
	}

	@Override
	public List<GradeResponseDTO> getGradesOfStudentASList(Integer id) {
		
		List<Grade> studentGradesList = gradesRepository.findByStudentId(id);
		
		return gradeCalculatorService.calculateStudentAverageGrade(studentGradesList);
	}
}
