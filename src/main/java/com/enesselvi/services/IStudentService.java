package com.enesselvi.services;

import java.net.ResponseCache; 
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.enesselvi.StudentDto.DtoStudent;
import com.enesselvi.StudentDto.DtoStudentSave;
import com.enesselvi.StudentDto.DtoStudentUpdate;
import com.enesselvi.entites.Student;

public interface IStudentService {
	
	public DtoStudent saveStudent(DtoStudentSave dtoStudentSave); //DtoStudent will returned ony name , lastname and number will returned
	
	
	public List<DtoStudent> getAllStudents();
	
	
	public String deleteStudent(Integer id);
	
	public DtoStudent getStudentById(Integer id);
	
	public DtoStudent updateStudent(Integer number , DtoStudentUpdate dtoStudentUpdate);
	
	public DtoStudent findStudentByNumber(Integer number);

}
