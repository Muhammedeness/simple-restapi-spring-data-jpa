package com.enesselvi.controller;
//interface ler oluşturulurken başına genelde I koyulur bu bir standarttır

import java.util.List;

import com.enesselvi.entites.Student;

public interface IStudentController {

	public Student saveStudent(Student student);
	
	
	public List<Student> getAllStudents();
	
	public void deleteStudent(Integer id);
	
	public Student getStudentById(Integer id);
	
	public Student updateStudent(Integer id , Student updatedStudent);
	
	public Student findStudentByNumber(Integer number);


	
}
