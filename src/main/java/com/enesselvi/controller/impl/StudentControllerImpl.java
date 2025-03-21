package com.enesselvi.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enesselvi.controller.IStudentController;
import com.enesselvi.entites.Student;
import com.enesselvi.services.IStudentService;


@RestController
@RequestMapping("/rest/api/student")
public class StudentControllerImpl implements IStudentController {

	@Autowired
	private IStudentService studentService ;
	
	
	
	@PostMapping(path = "/save")
	@Override
	public Student saveStudent(@RequestBody Student student) {
		
		return studentService.saveStudent(student);
	}

	
	
	@DeleteMapping("/delete/{id}")
	@Override
	public ResponseEntity<String> deleteStudent(@PathVariable(name = "id")Integer id) {
		return studentService.deleteStudent(id);
		
	}

	@GetMapping("/list")
	@Override
	public List<Student> getAllStudents() {
		
	  return studentService.gettAllStudents();
	}


    @GetMapping("/list/{id}")
	@Override
	public Student getStudentById(@PathVariable(name = "id")Integer id) {
    	
		return studentService.getStudentById(id);
	}


    @PutMapping("/update/{id}")
	@Override
	public Student updateStudent(@PathVariable(name = "id") Integer id,  @RequestBody  Student updatedStudent) {

    	return studentService.updateStudent(id, updatedStudent); 
	}


    @GetMapping("/list/with-number/{stuNumber}")
	@Override
	public Student findStudentByNumber(@PathVariable(name = "stuNumber")Integer number) {
    	
		return studentService.findStudentByNumber(number);
	}





}
