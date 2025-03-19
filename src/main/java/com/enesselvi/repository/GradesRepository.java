package com.enesselvi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enesselvi.entites.grades;

public interface GradesRepository   extends JpaRepository<grades, Integer>{

	
    // Öğrencinin ID'sine göre tüm notları getir
    List<grades> findByStudentId(Integer studentId);
}
