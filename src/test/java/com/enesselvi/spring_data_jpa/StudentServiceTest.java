package com.enesselvi.spring_data_jpa;

import com.enesselvi.Exception.CustomAlreadyInDatabaseException; 
import com.enesselvi.Exception.CustomNotFoundException;
import com.enesselvi.Exception.CustomNullException;
import com.enesselvi.StudentDto.DtoStudent;
import com.enesselvi.StudentDto.DtoStudentSave;
import com.enesselvi.StudentDto.DtoStudentUpdate;
import com.enesselvi.entites.Student;
import com.enesselvi.repository.StudentRepository;
import com.enesselvi.services.impl.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private DtoStudentSave dtoStudentSave;
    private DtoStudent dtoStudent;
    private Student student;

    @BeforeEach
    void setUp() {
    	
        dtoStudentSave = new DtoStudentSave();
        dtoStudentSave.setFirstName("John");
        dtoStudentSave.setLastName("Doe");
        dtoStudentSave.setStuNumber(123);
        //dtoStudentSave.setBirthOfDate(2000-01-01);

        dtoStudent = new DtoStudent();
        dtoStudent.setFirstName("John");
        dtoStudent.setLastName("Doe");
        dtoStudent.setStuNumber(123);

        student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setStuNumber(123);
       // student.setBirthOfDate("2000-01-01");
    }

    @Test
    void saveStudent_shouldSaveStudentAndReturnDto() {
        
    	when(studentRepository.findOne(any())).thenReturn(Optional.empty()); // Öğrencinin veritabanında olmadığını simüle ediyoruz
        when(studentRepository.save(any())).thenReturn(student); // Kaydedilen öğrenciyi simüle ediyoruz

        
        DtoStudent result = studentService.saveStudent(dtoStudentSave);

        
        assertNotNull(result);
        assertEquals(dtoStudent.getFirstName(), result.getFirstName());
        assertEquals(dtoStudent.getLastName(), result.getLastName());
        assertEquals(dtoStudent.getStuNumber(), result.getStuNumber());
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    void saveStudent_shouldThrowExceptionIfStudentAlreadyExists() {

        when(studentRepository.findOne(any())).thenReturn(Optional.of(student)); // Öğrencinin veritabanında olduğunu simüle ediyoruz


        assertThrows(CustomAlreadyInDatabaseException.class, () -> {
            studentService.saveStudent(dtoStudentSave);
        });

        verify(studentRepository, times(0)).save(any()); // Save metodunun çağrılmadığını kontrol ediyoruz
    }

    @Test
    void saveStudent_shouldThrowExceptionIfAnyFieldIsNull() {

        DtoStudentSave invalidDto = new DtoStudentSave(); // Tüm alanları null olan bir DTO


        assertThrows(CustomNullException.class, () -> {
            studentService.saveStudent(invalidDto);
        });

        verify(studentRepository, times(0)).save(any());
    }

    @Test
    void deleteStudent_shouldDeleteStudentIfFound() {

        int id = 1;
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));


        String result = studentService.deleteStudent(id);


        assertEquals("Kullanıcı başarıyla silindi", result);
        verify(studentRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteStudent_shouldThrowExceptionIfNotFound() {
        
    	int id = 1;
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        
        assertThrows(CustomNotFoundException.class, () -> {
            studentService.deleteStudent(id);
        });

        verify(studentRepository, times(0)).deleteById(anyInt());
    }

    @Test
    void getAllStudents_shouldReturnListOfDtoStudents() {
        
    	
    	List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        when(studentRepository.findAll()).thenReturn(studentList);

        
        List<DtoStudent> result = studentService.getAllStudents();

        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(dtoStudent.getFirstName(), result.get(0).getFirstName());
        assertEquals(dtoStudent.getLastName(), result.get(0).getLastName());
        assertEquals(dtoStudent.getStuNumber(), result.get(0).getStuNumber());
    }

    @Test
    void getAllStudents_shouldThrowExceptionIfListIsEmpty() {
        
    	
    	when(studentRepository.findAll()).thenReturn(new ArrayList<>());

        
        assertThrows(CustomNotFoundException.class, () -> {
            studentService.getAllStudents();
        });
    }

    @Test
    void getStudentById_shouldReturnDtoStudentIfFound() {
        
    	int id = 1;
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        
        DtoStudent result = studentService.getStudentById(id);

        
        assertNotNull(result);
        assertEquals(dtoStudent.getFirstName(), result.getFirstName());
        assertEquals(dtoStudent.getLastName(), result.getLastName());
        assertEquals(dtoStudent.getStuNumber(), result.getStuNumber());
    }

    @Test
    void getStudentById_shouldThrowExceptionIfNotFound() {
        
    	int id = 1;
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        
        assertThrows(CustomNotFoundException.class, () -> {
            studentService.getStudentById(id);
        });
    }

    @Test
    void updateStudent_shouldUpdateStudentAndReturnDtoIfFound() {
        
    	
    	int number = 123;
        DtoStudentUpdate dtoStudentUpdate = new DtoStudentUpdate();
        dtoStudentUpdate.setFirstName("Jane");
        dtoStudentUpdate.setLastName("Doe");
       // dtoStudentUpdate.setBirthOfDate("2001-01-01");

        Student existingStudent = new Student();
        existingStudent.setStuNumber(number);
        existingStudent.setFirstName("John"); // Önemli: Mevcut öğrenci bilgilerini ayarlıyoruz
        existingStudent.setLastName("Doe");
        //existingStudent.setBirthOfDate("2000-01-01");

        when(studentRepository.findOne(any())).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any())).thenReturn(existingStudent);

        
        DtoStudent result = studentService.updateStudent(number, dtoStudentUpdate);

        
        assertNotNull(result);
        assertEquals("Jane", result.getFirstName()); // Güncellenen ismin doğru olduğunu kontrol ediyoruz
        assertEquals("Doe", result.getLastName());
        assertEquals(number, result.getStuNumber());
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    void updateStudent_shouldThrowExceptionIfNotFound() {

    	int number = 123;
        DtoStudentUpdate dtoStudentUpdate = new DtoStudentUpdate();
        when(studentRepository.findOne(any())).thenReturn(Optional.empty());

        
        assertThrows(CustomNotFoundException.class, () -> {
            studentService.updateStudent(number, dtoStudentUpdate);
        });

        verify(studentRepository, times(0)).save(any());
    }

    @Test
    void findStudentByNumber_shouldReturnDtoStudentIfFound() {
        
    	int number = 123;
        when(studentRepository.findOne(any())).thenReturn(Optional.of(student));

        
        DtoStudent result = studentService.findStudentByNumber(number);

        
        assertNotNull(result);
        assertEquals(dtoStudent.getFirstName(), result.getFirstName());
        assertEquals(dtoStudent.getLastName(), result.getLastName());
        assertEquals(dtoStudent.getStuNumber(), result.getStuNumber());
    }

    @Test
    void findStudentByNumber_shouldThrowExceptionIfNotFound() {
        
    	int number = 123;
        when(studentRepository.findOne(any())).thenReturn(Optional.empty());


        assertThrows(CustomNotFoundException.class, () -> {
            studentService.findStudentByNumber(number);
        });
    }
}