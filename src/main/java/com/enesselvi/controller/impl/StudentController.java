package com.enesselvi.controller.impl;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enesselvi.StudentDto.DtoStudent;
import com.enesselvi.StudentDto.DtoStudentSave;
import com.enesselvi.StudentDto.DtoStudentUpdate;
import com.enesselvi.controller.IStudentController;
import com.enesselvi.services.IStudentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/rest/api/student")
@CrossOrigin(origins = "http://localhost:5500") 
public class StudentController implements IStudentController {

	@Autowired
	private IStudentService studentService ;
	
	
	
	@PostMapping(path = "/save")
	@Override
	public DtoStudent saveStudent(@RequestBody @Valid DtoStudentSave dtoStudentSave) {
		
		return studentService.saveStudent(dtoStudentSave);
	}

	
	
	@DeleteMapping("/delete/{id}")
	@Override
	public String deleteStudent(@PathVariable(name = "id")Integer id) {
		return studentService.deleteStudent(id);
		
	}

	@GetMapping("/list")
	@Override
	public List<DtoStudent> getAllStudents() {
		
		List<DtoStudent> dtoStudentList = studentService.getAllStudents();

	  return dtoStudentList;
	}


    @GetMapping("/list/{id}")
	@Override
	public DtoStudent getStudentById(@PathVariable(name = "id")Integer id) {
    	
		return studentService.getStudentById(id);
	}


    @PutMapping("/update/{num}")
	@Override
	public DtoStudent updateStudent(@PathVariable(name = "num") Integer num,  @RequestBody @Valid DtoStudentUpdate dtoStudentUpdate) {

    	return studentService.updateStudent(num, dtoStudentUpdate); 
	}


    @GetMapping("/list/with-number/{stuNumber}")
	@Override
	public DtoStudent  findStudentByNumber(@PathVariable(name = "stuNumber")Integer number) {
    	
		return studentService.findStudentByNumber(number);
	}

}
