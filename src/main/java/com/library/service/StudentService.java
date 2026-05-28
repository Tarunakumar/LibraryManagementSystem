package com.library.service;

import java.util.List;

import com.library.entity.Student;

public interface StudentService {

    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(Integer id);

    Student updateStudent(Integer id, Student student);

    String deleteStudent(Integer id);
}