package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class DemoApplication {


    public static void main(String[] args) throws IOException {

        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Student>> typeReference = new TypeReference<List<Student>>(){};
        StudentRepository studentRepository = context.getBean(StudentRepository.class);
        try {
            List<Student> students = mapper.readValue(new File("src/main/resources/static/students.json"), typeReference);
            for (Student student : students) {
                studentRepository.save(student);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.save(new User("user", "password", true, "ADMIN"));

    }


}
