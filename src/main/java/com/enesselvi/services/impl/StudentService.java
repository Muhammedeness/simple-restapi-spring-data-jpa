package com.enesselvi.services.impl;

import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.enesselvi.Exception.CustomAlreadyInDatabaseException;
import com.enesselvi.Exception.CustomNotFoundException;
import com.enesselvi.Exception.CustomNullException;
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
	
	@Override
	public DtoStudent saveStudent(DtoStudentSave dtoStudentSave){
		
		DtoStudent dtoStudent = new DtoStudent();
		Student student = new Student();
		BeanUtils.copyProperties(dtoStudentSave, student);
		
		//EĞER NULL VERİ GÖNDERİLİRSE HATA FIRLAT
		if (student.getFirstName()==null ||  student.getLastName()==null || student.getBirthOfDate()==null || student.getStuNumber()==null) {
			throw new CustomNullException("Lütfen Boş yerleri doldurunuz");
		}

		Example<Student> example = Example.of(student);
		Optional<Student> optional = studentRepository.findOne(example);
		
		if (!optional.isPresent()) {
			studentRepository.save(student);
			BeanUtils.copyProperties(student, dtoStudent);
			return dtoStudent;
		} 
		throw new CustomAlreadyInDatabaseException("Öğrenci Zaten Kayıtlı");
	}    
	
	
	
	@Override
	public String deleteStudent(Integer id){
	
		Optional<Student> optional = studentRepository.findById(id);
		if (optional.isPresent()) {
			studentRepository.deleteById(id);    
			return "Kullanıcı başarıyla silindi";
		}
		
		throw new CustomNotFoundException("Kullanıcı Bulunamadı");
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
		
		if (dtoStudentList.isEmpty()) {
			throw new CustomNotFoundException("Liste Boşşşşş");
		}
		return   dtoStudentList;
	}

	
	
	@Override
	public DtoStudent getStudentById(Integer id) {
		DtoStudent dtoStudent = new DtoStudent();
		Optional<Student> optional = studentRepository.findById(id);
		if (optional.isPresent()) {
			
			BeanUtils.copyProperties(optional.get(), dtoStudent);
			return dtoStudent;
		}
		throw new CustomNotFoundException("Girilen ID de Öğrenci Bulunamadı");
	}
	
	
	

	@Override
	public DtoStudent updateStudent(Integer number, DtoStudentUpdate dtoStudentUpdate) {
		
		Student dbStudent = new Student();
		DtoStudent dtoStudent = new DtoStudent();
		dbStudent.setStuNumber(number);
		Example<Student> example = Example.of(dbStudent);
		Optional<Student> optional = studentRepository.findOne(example);
		if (optional.isPresent()) {
			
			dbStudent = optional.get();
			dbStudent.setFirstName(dtoStudentUpdate.getFirstName());
			dbStudent.setLastName(dtoStudentUpdate.getLastName());
			dbStudent.setBirthOfDate(dtoStudentUpdate.getBirthOfDate());
			
		    Student updatedStudent = studentRepository.save(dbStudent);
			BeanUtils.copyProperties(updatedStudent, dtoStudent);
			
			return dtoStudent;
		}
	   throw new  CustomNotFoundException("Öğrenci BUlunamadı. Güncelleme İşleme Yapılamadı");
	}

	
	
	@Override
	public DtoStudent findStudentByNumber(Integer number) {
		
		Student findStudent = new Student();
		findStudent.setStuNumber(number);
		
		Example<Student> example = Example.of(findStudent);
		Optional<Student> optional = studentRepository.findOne(example);
		if (optional.isPresent()) {
			
			DtoStudent dtoStudent = new DtoStudent();
			
			findStudent=optional.get();
			BeanUtils.copyProperties(findStudent, dtoStudent);
			
			return dtoStudent;
		}
		   throw new  CustomNotFoundException("Girilen NUmarada ki Öğrenci Bulunamadı");
	}
}
