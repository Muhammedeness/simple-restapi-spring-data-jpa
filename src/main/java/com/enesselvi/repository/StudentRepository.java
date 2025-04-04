package com.enesselvi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.enesselvi.entites.Student;



@Repository
public interface StudentRepository  extends JpaRepository<Student, Integer>{
	

	Student findByStuNumber(Integer number);
	Boolean existsByStuNumber(Integer number);
	//Student deleteByStuNumber(Integer number);
	
}
