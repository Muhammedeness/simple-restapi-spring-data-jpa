package com.enesselvi.controller;
//interface ler oluşturulurken başına genelde I koyulur bu bir standarttır

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.enesselvi.StudentDto.DtoStudent;
import com.enesselvi.StudentDto.DtoStudentSave;
import com.enesselvi.StudentDto.DtoStudentUpdate;
import com.enesselvi.entites.Student;

public interface IStudentController {

	public DtoStudent saveStudent(DtoStudentSave dtoStudentSave);
	
	
	public List<DtoStudent> getAllStudents();
	
	public String deleteStudent(Integer id);
	
	public DtoStudent getStudentById(Integer id);
	
	public ResponseEntity<?> updateStudent(Integer number , DtoStudentUpdate dtoStudentUpdate);
	
	public ResponseEntity<?>  findStudentByNumber(Integer number);


	
}
