package com.enesselvi.spring_data_jpa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;

import com.enesselvi.Exception.CustomNotFoundException;
import com.enesselvi.GradeDto.DtoGrade;
import com.enesselvi.GradeDto.DtoGradeAdd;
import com.enesselvi.GradeDto.DtoGradeList;
import com.enesselvi.GradeDto.DtoGradeResponse;
import com.enesselvi.entites.Grade;
import com.enesselvi.entites.Student;
import com.enesselvi.repository.GradesRepository;
import com.enesselvi.repository.StudentRepository;
import com.enesselvi.services.impl.GradeCalculatorService;
import com.enesselvi.services.impl.GradeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GradeServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private GradesRepository gradesRepository;

    @Mock
    private GradeCalculatorService gradeCalculatorService;

    @InjectMocks
    private GradeService gradeService;

    
    @Test
    public void testSaveGrade_StudentNotFound() {
        Integer number = 123;
        DtoGradeAdd dtoGradeAdd = new DtoGradeAdd();

        when(studentRepository.findOne(any(Example.class))).thenReturn(Optional.empty());

        assertThrows(CustomNotFoundException.class, () -> gradeService.saveGrade(number, dtoGradeAdd));
    }
    
    @Test
    public void testSaveGrade_Success() {
        Integer number = 123;
        DtoGradeAdd dtoGradeAdd = new DtoGradeAdd();
        Student student = new Student();
        student.setStuNumber(number);
        Grade grade = new Grade();
        BeanUtils.copyProperties(dtoGradeAdd, grade);
        grade.setStudent(student);

        when(studentRepository.findOne(any(Example.class))).thenReturn(Optional.of(student));
        when(gradesRepository.save(any(Grade.class))).thenReturn(grade);

        DtoGrade result = gradeService.saveGrade(number, dtoGradeAdd);

        assertNotNull(result);
        verify(gradesRepository, times(1)).save(any(Grade.class));
    }
   
    
    @Test
    public void testListAllGrades_GradesNotFound() {
        when(gradesRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(CustomNotFoundException.class, () -> gradeService.listAllGrades());
    }
    
    @Test
    public void testListAllGrades_Success() {
        List<Grade> grades = new ArrayList<>();
        Grade grade = new Grade();
        Student student = new Student();
        student.setFirstName("Test");
        student.setLastName("User");
        grade.setStudent(student);
        grades.add(grade);

        when(gradesRepository.findAll()).thenReturn(grades);

        List<DtoGradeList> result = gradeService.listAllGrades();

        assertFalse(result.isEmpty());
        assertEquals("Test", result.get(0).getFirstName());
    }
    
    @Test
    public void testGetGradesOfStudentASList() {
        Integer studentId = 1;
        List<Grade> grades = new ArrayList<>();
        List<DtoGradeResponse> expectedResponse = new ArrayList<>();

        when(gradesRepository.findByStudentId(studentId)).thenReturn(grades);
        when(gradeCalculatorService.calculateStudentAverageGrade(grades)).thenReturn(expectedResponse);

        List<DtoGradeResponse> result = gradeService.getGradesOfStudentASList(studentId);

        assertEquals(expectedResponse, result);
    }

}