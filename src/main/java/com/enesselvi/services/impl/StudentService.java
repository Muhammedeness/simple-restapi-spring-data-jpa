package com.enesselvi.services.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.enesselvi.StudentDto.DtoStudent;
import com.enesselvi.StudentDto.DtoStudentSave;
import com.enesselvi.StudentDto.DtoStudentUpdate;
import com.enesselvi.entites.Student;
import com.enesselvi.repository.StudentRepository;
import com.enesselvi.services.IStudentService;


@Service
public class StudentService implements IStudentService {
	
	
	@Autowired
	private StudentRepository studentRepository;
	

	
	//////////////Öğrenci Kayıt İşlemi/////////////////////
	@Override
	public ResponseEntity<?> saveStudent(DtoStudentSave dtoStudentSave){
		
		DtoStudent dtoStudent = new DtoStudent();
		Student student = new Student();
		BeanUtils.copyProperties(dtoStudentSave, student);
        //aynı numaralı öğrenci kontrol ediyor varsa eklemiyor
		Example<Student> example = Example.of(student);
		Optional<Student> optional = studentRepository.findOne(example);
		if (!optional.isPresent()) {
			studentRepository.save(student);
			BeanUtils.copyProperties(student, dtoStudent);
			return ResponseEntity.ok(dtoStudent);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Kullanıcı zaten kayıtlı");
	}    
	
	
	@Override
	public ResponseEntity<String> deleteStudent(Integer id){
	
		Optional<Student> optional = studentRepository.findById(id);
		if (optional.isPresent()) {
			studentRepository.deleteById(id);    
			return ResponseEntity.status(HttpStatus.OK).body("Kullanıcı başarıyla silindi");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kullanıcı bulunamadı.");
	}

	
	@Override
	public List<DtoStudent> getAllStudents() {
		
		List<DtoStudent> dtoStudentList=new ArrayList<>(); //Döndürelecek liste
		
		List<Student> studentList = studentRepository.findAll();
		
		for (Student student : studentList) {
			DtoStudent dtoStudent = new DtoStudent();
			BeanUtils.copyProperties(student, dtoStudent);
			dtoStudentList.add(dtoStudent);
		}
		return dtoStudentList;
	}

	@Override
	public ResponseEntity<?> getStudentById(Integer id) {
		DtoStudent dtoStudent = new DtoStudent();
		Optional<Student> optional = studentRepository.findById(id);
		if (optional.isPresent()) {
			
			BeanUtils.copyProperties(optional.get(), dtoStudent);
			return ResponseEntity.ok(dtoStudent);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Kullanıcı Bulunamadı");
	}

	@Override
	public ResponseEntity<?> updateStudent(Integer number, DtoStudentUpdate dtoStudentUpdate) {
		
		Student dbStudent = new Student();
		DtoStudent dtoStudent = new DtoStudent();
		dbStudent.setStuNumber(number);
		Example<Student> example = Example.of(dbStudent);
		Optional<Student> optional = studentRepository.findOne(example);
		if (optional.isPresent()) {
			

			dbStudent = optional.get();

			System.out.println(dbStudent.getId());
			dbStudent.setFirstName(dtoStudentUpdate.getFirstName());
			dbStudent.setLastName(dtoStudentUpdate.getLastName());
			dbStudent.setBirthOfDate(dtoStudentUpdate.getBirthOfDate());
			
		    Student updatedStudent = studentRepository.save(dbStudent);
 
			BeanUtils.copyProperties(updatedStudent, dtoStudent);
			return ResponseEntity.ok(dtoStudent);
		}
	return	ResponseEntity.status(HttpStatus.CONFLICT).body("Kullanıcı Bulunamadı");
		
	}

	
	@Override
	public ResponseEntity<?> findStudentByNumber(Integer number) {
		
		
		Student findStudent = new Student();
		findStudent.setStuNumber(number);
		
		Example<Student> example = Example.of(findStudent);
		Optional<Student> optional = studentRepository.findOne(example);
		if (optional.isPresent()) {
			
			DtoStudent dtoStudent = new DtoStudent();
			
			findStudent=optional.get();
			BeanUtils.copyProperties(findStudent, dtoStudent);
			
			return ResponseEntity.ok(dtoStudent);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Kullanıcı Bulunamadı");
	}
}
