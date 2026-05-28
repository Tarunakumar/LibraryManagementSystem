package com.library.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.entity.Student;
import com.library.exception.StudentNotFoundException;
import com.library.repository.StudentRepository;
import com.library.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    
    public StudentServiceImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    
    @Override
    public Student saveStudent(Student student) {

        return studentRepository.save(student);
    }

    
    @Override
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    
    @Override
    public Student getStudentById(Integer id) {

        return studentRepository.findById(id).orElseThrow(() 
        		-> new StudentNotFoundException("student is not found with this id:" + id));
    }

    
    @Override
    public Student updateStudent(Integer id, Student student) {

        Student existingStudent =
                studentRepository.findById(id).orElse(null);

        if(existingStudent != null) {

            existingStudent.setName(student.getName());
            existingStudent.setUsn(student.getUsn());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setMobile(student.getMobile());

            return studentRepository.save(existingStudent);
        }

        return null;
    }

    
    @Override
    public String deleteStudent(Integer id) {

        studentRepository.deleteById(id);

        return "Student Deleted Successfully";
    }
}