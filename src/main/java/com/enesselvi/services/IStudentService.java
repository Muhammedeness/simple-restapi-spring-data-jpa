package com.enesselvi.services;

import java.util.List;

import com.enesselvi.entites.Student;

public interface IStudentService {
	
	public Student saveStudent(Student student);
	
	
	public List<Student> gettAllStudents();
	
	
	public void deleteStudent(Integer id);
	
	public Student getStudentById(Integer id);
	
	public Student updateStudent(Integer id , Student updatedStudent);
	
	public Student findStudentByNumber(Integer number);

}
