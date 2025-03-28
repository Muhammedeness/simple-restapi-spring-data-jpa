package com.enesselvi.services.impl;

import java.util.ArrayList;   
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.enesselvi.Exception.CustomErrorException;
import com.enesselvi.Exception.CustomNotFoundException;
import com.enesselvi.GradeDto.DtoGrade;
import com.enesselvi.GradeDto.DtoGradeAdd;
import com.enesselvi.GradeDto.DtoGradeList;
import com.enesselvi.GradeDto.DtoGradeResponse;
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
	public DtoGrade saveGrade(Integer number  , DtoGradeAdd dtoGradeAdd) {

		Grade addGrade = new Grade();   //VERİTABANINA EKLENECEK NOTUN TUTULDUĞU DEĞİŞKEN
		DtoGrade dtoGrade = new DtoGrade();  //NOTUN KULLANICIYA DÖNDÜRÜLECEĞİ DTOSU
		Student student = new Student();  //İSTENEN NUMARADAKİ KULLANICIYI ATANACAK DEĞİŞKEN
		student.setStuNumber(number);
		Example<Student> example = Example.of(student);
		Optional<Student> optional = studentRepository.findOne(example);  //VERİLEN ÖĞRENCİYİ ARAYAN KOD. ARKADA WHERE Lİ SQL SORGUSU ÇALIŞIR

		
		
		if (!optional.isPresent()) {
			throw new CustomNotFoundException("Girilen Numara ile Öğrenci Bulunamadı");
		}
		
		if (optional.isPresent()) {
			 student = optional.get(); //BULUNAN ÖĞRENCİYİ GETTER İLE ATA
			 BeanUtils.copyProperties(dtoGradeAdd, addGrade);
			 
			 addGrade.setStudent(student);
			 Grade savedGrade = gradesRepository.save(addGrade);
			 BeanUtils.copyProperties(savedGrade, dtoGrade);

			return dtoGrade;
		}
		
		throw new CustomErrorException("Hatalı Durum");
	}

	@Override
	public List<DtoGradeList> listAllGrades() {
		
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
			return dtoGradesList;
		}
		throw new CustomNotFoundException("Notlar Bulunamadı");
	}

	@Override
	public List<DtoGradeResponse> getGradesOfStudentASList(Integer id) {
		
		List<Grade> studentGradesList = gradesRepository.findByStudentId(id);
		
		return gradeCalculatorService.calculateStudentAverageGrade(studentGradesList);
	}
}
