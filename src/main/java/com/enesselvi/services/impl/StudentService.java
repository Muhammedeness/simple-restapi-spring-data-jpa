package com.enesselvi.services.impl;



import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.enesselvi.entites.Student;
import com.enesselvi.repository.StudentRepository;
import com.enesselvi.services.IStudentService;





@Service
public class StudentService implements IStudentService {
	
	
	@Autowired
	private StudentRepository studentRepository;
	

	@Override
	public Student saveStudent(Student student){
				
		return studentRepository.save(student);
	}
	
	@Override
	public void deleteStudent(Integer id){
		
	 studentRepository.deleteById(id);
	}

	@Override
	public List<Student> gettAllStudents() {
		
		List<Student> studentList = studentRepository.findAll();
		return studentList;
	}

	@Override
	public Student getStudentById(Integer id) {
		
		Optional<Student> optional = studentRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Student updateStudent(Integer id, Student updatedStudent) {
		
		Student dbStudent =  getStudentById(id);
		if (dbStudent!=null ) {
			dbStudent.setFirstName(updatedStudent.getFirstName());
			dbStudent.setLastName(updatedStudent.getLastName());
			dbStudent.setBirthOfDate(updatedStudent.getBirthOfDate());
			dbStudent.setStuNumber(updatedStudent.getStuNumber());
			
			studentRepository.save(dbStudent); //burada save override edildi
		}
		return null;
	}

	@Override
	public Student findStudentByNumber(Integer number) {
		
		Student findStudent = new Student();
		findStudent.setStuNumber(number);
		
		Example<Student> example = Example.of(findStudent);
		Optional<Student> optional = studentRepository.findOne(example);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
