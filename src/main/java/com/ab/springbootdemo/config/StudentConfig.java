package com.ab.springbootdemo.config;

import com.ab.springbootdemo.entity.Student;
import com.ab.springbootdemo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student eren = new Student(
                    "Eren",
                    "eren@gmail.com",
                    LocalDate.of(2011, Month.SEPTEMBER, 27));
            Student kucuk = new Student(
                    "Kucuk",
                    "kucuk@gmail.com",
                    LocalDate.of(2021,
                            Month.SEPTEMBER,
                            27));

            studentRepository.saveAll(List.of(eren, kucuk));
        };
    }
}
