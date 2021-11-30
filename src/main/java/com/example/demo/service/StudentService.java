package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAddress(String address) {
        return studentRepository.findByAddress(address);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Student putStudent(Long id, Student student) {
        Student putStudent = getStudent(id);
        if (student.getAddress() != null)
            putStudent.setAddress(student.getAddress());
        if (student.getName() != null)
            putStudent.setName(student.getName());
        return studentRepository.save(putStudent);
    }



}
