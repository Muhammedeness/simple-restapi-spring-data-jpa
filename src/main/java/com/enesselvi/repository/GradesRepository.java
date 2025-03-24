package com.enesselvi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enesselvi.entites.Grade;

public interface GradesRepository   extends JpaRepository<Grade, Integer>{

	
    // Öğrencinin ID'sine göre tüm notları getir
    List<Grade> findByStudentId(Integer studentId);
}
