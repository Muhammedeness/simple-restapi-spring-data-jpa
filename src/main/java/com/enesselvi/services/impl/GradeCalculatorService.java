package com.enesselvi.services.impl;

import java.util.ArrayList;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enesselvi.entites.GradeResponseDTO;
import com.enesselvi.entites.grades;
//import com.enesselvi.repository.GradesRepository;
import com.enesselvi.services.IGradeCalculaterService;

@Service
public class GradeCalculatorService implements IGradeCalculaterService {

    @Override
    public List<GradeResponseDTO> calculateStudentAverageGrade(List<grades> gradesList) {
        List<GradeResponseDTO> responseList = new ArrayList<>();

        for (grades grade : gradesList) {
            double vize = (grade.getMidTermGrade() != null) ? grade.getMidTermGrade() : 0.0;
            double fin = (grade.getFinalGrade() != null) ? grade.getFinalGrade() : 0.0;
            double makeup = (grade.getMakeupGrade() != null) ? grade.getMakeupGrade() : 0.0;

            double avg;
            if (makeup == 0.0) {
                avg = (vize * 0.4) + (fin * 0.6);
            } else {
                avg = (vize * 0.4) + (makeup * 0.6);
            }

            String letterGrade = calculateGradeCode(avg);

            responseList.add(new GradeResponseDTO(
                    grade.getId(),
                    vize,
                    fin,
                    makeup,
                    avg,
                    letterGrade
            ));    
        }
        return responseList;
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
