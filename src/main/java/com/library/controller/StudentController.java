package com.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.library.entity.Student;
import com.library.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController {

    private StudentService studentService;

    
    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    
    @PostMapping
    public Student saveStudent(
            @Valid @RequestBody Student student) {

        return studentService.saveStudent(student);
    }

    
    @GetMapping
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();
    }

    
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Integer id) {

        return studentService.getStudentById(id);
    }

    
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Integer id,
            @RequestBody Student student) {

        return studentService.updateStudent(id, student);
    }

    
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id) {

        return studentService.deleteStudent(id);
    }
}