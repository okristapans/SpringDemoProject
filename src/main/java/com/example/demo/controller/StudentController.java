package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class StudentController {

    private final StudentService studentService;
    private static Logger logger = LoggerFactory.getLogger(StudentController.class);


    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String home() {
        return ("<h1>Home<h1>");
    }


    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping("/students")
    public List<Student> getStudentsByAddress(@RequestParam(defaultValue = "") String address) {
       if(address.isEmpty()) {
           return studentService.getStudents();
       }
       else {
           return studentService.getAddress(address);
       }
    }

    @PostMapping("/students")
    public Student postStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }


    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping("/students/{id}")
    public void updateStudent(@PathVariable(value = "id") Long id, @RequestBody Student student) {
        studentService.putStudent(id, student);
    }


}
