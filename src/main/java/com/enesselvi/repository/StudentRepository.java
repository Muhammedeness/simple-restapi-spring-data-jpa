package com.enesselvi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.enesselvi.entites.Student;

import jakarta.transaction.Transactional;


@Repository
public interface StudentRepository  extends JpaRepository<Student, Integer>{
	
	/*
	@Modifying
	@Transactional
	@Query(value = "UPDATE student SET number =: userNumber WHERE id =: userId" , nativeQuery = true)
    void UpdateUserNumber(@Param("userNumber") Integer number ,   @Param("userId") Integer id);*/
	
}
