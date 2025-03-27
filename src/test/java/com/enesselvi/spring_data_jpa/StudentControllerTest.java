package com.enesselvi.spring_data_jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.enesselvi.Exception.CustomNotFoundException;
import com.enesselvi.Exception.CustomNullException;
import com.enesselvi.StudentDto.DtoStudent;
import com.enesselvi.StudentDto.DtoStudentSave;
import com.enesselvi.StudentDto.DtoStudentUpdate;
import com.enesselvi.controller.impl.StudentController;
import com.enesselvi.services.IStudentService;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
	
	
	@Mock
	private IStudentService studentService;
	
	@InjectMocks
	private StudentController studentController;
	
	
    private DtoStudentSave dtoStudentSave;
    private DtoStudent dtoStudent;
    private DtoStudentUpdate dtoStudentUpdate;
    
    
    @BeforeEach
    void setUp() {
       dtoStudentSave = new DtoStudentSave();
        dtoStudentSave.setFirstName("John");
        dtoStudentSave.setLastName("Doe");
        dtoStudentSave.setStuNumber(123);
        dtoStudentSave.setBirthOfDate("2000-01-01"); 

       dtoStudent = new DtoStudent();
        dtoStudent.setFirstName("John");
        dtoStudent.setLastName("Doe");
        dtoStudent.setStuNumber(123);
        
  
        

        dtoStudentUpdate = new DtoStudentUpdate();
        dtoStudentUpdate.setFirstName("Jane");
        dtoStudentUpdate.setLastName("Doe");
        dtoStudentUpdate.setBirthOfDate("2001-01-01"); 
    }
    
    
    @Test
    void saveStudent_shouldReturnSavedStudent() {//saveStudent controller ı eklenen öğrenciyi döndürmeli
    	
    	
        when(studentService.saveStudent(dtoStudentSave)).thenReturn(dtoStudent);

        System.out.println(dtoStudentSave.getStuNumber());
        System.out.println(dtoStudent.getStuNumber());
        DtoStudent result = studentController.saveStudent(dtoStudentSave);
        System.out.println(result.getStuNumber());

		assertEquals(dtoStudent, result);
        verify(studentService, times(1)).saveStudent(dtoStudentSave);
    }
    
    
    
    @Test
    void deleteStudent_shouldReturnSuccessMessage() {
        int id = 1;
        when(studentService.deleteStudent(id)).thenReturn("Kullanıcı başarıyla silindi");

        String result = studentController.deleteStudent(id);

        assertEquals("Kullanıcı başarıyla silindi", result);
        verify(studentService, times(1)).deleteStudent(id);
    }
    
    
    
    
    @Test
    void getAllStudents_shouldReturnListOfStudents() {
        List<DtoStudent> students = Arrays.asList(dtoStudent, new DtoStudent());
        when(studentService.getAllStudents()).thenReturn(students);

        List<DtoStudent> result = studentController.getAllStudents();

        assertEquals(students, result);
        verify(studentService, times(1)).getAllStudents();
    }
    
    
    @Test
    void getStudentById_shouldReturnStudentById() {
        int id = 1;
        when(studentService.getStudentById(id)).thenReturn(dtoStudent);

        DtoStudent result = studentController.getStudentById(id);

        assertEquals(dtoStudent, result);
        verify(studentService, times(1)).getStudentById(id);
    }
    
    

    @Test
    void updateStudent_shouldReturnUpdatedStudent() {
        int num = 123;
        when(studentService.updateStudent(num, dtoStudentUpdate)).thenReturn(dtoStudent);

        DtoStudent result = studentController.updateStudent(num, dtoStudentUpdate);

        assertEquals(dtoStudent, result);
        verify(studentService, times(1)).updateStudent(num, dtoStudentUpdate);
    }

    
    
    @Test
    void findStudentByNumber_shouldReturnStudentByNumber() {
        int number = 123;
        when(studentService.findStudentByNumber(number)).thenReturn(dtoStudent);

        DtoStudent result = studentController.findStudentByNumber(number);

        assertEquals(dtoStudent, result);
        verify(studentService, times(1)).findStudentByNumber(number);
    }
    
}
