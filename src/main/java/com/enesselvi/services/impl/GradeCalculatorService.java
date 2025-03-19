package com.enesselvi.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enesselvi.entites.grades;
import com.enesselvi.repository.GradesRepository;
import com.enesselvi.services.IGradeCalculaterService;

@Service
public class GradeCalculatorService  implements IGradeCalculaterService{

	
	/*@Autowired
	GradesRepository gradesRepository;*/
	

	@Override
	public String calculateStudentAverageGrade(List<grades> gradesList) {
		
		if (gradesList.isEmpty()) {
			return "0.0";
		}
		
        double total = 0.0;
        int count = 0;

        for (grades grade : gradesList) {
            double vize = (grade.getMidTermGrade() != null) ? grade.getMidTermGrade() : 0.0;
            double fin = (grade.getFinalGrade() != null) ? grade.getFinalGrade() : 0.0;
            double makeup = (grade.getMakeupGrade() != null) ? grade.getMakeupGrade() : 0.0;

            // Eğer bütünleme yoksa vize %40 + final %60
            if (makeup == 0.0) {
                total += (vize * 0.4) + (fin * 0.6);
            } else {
                // Eğer bütünleme varsa, final yerine bütünleme kullan
                total += (vize * 0.4) + (makeup * 0.6);
            }
            count++;
        }
        
        
		return calculateGradeCode(total);
	}

	@Override
	public String calculateGradeCode(Double avg) {
		
		if (avg>=85) {
			return "AA";
		}else if (avg>=70) {
			return "BA";
		}else if (avg>=60) {
			return "BB";
		}else if (avg>=55) {
			return "CB";
		}else if (avg>=45) {
			return "CC";
		}else if (avg>=40) {
			return "DD";
		}else {
			return "FF";
		}
		
	}

	
}
