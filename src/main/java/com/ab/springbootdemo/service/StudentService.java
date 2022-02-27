package com.ab.springbootdemo.service;

import com.ab.springbootdemo.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();

    void addStudent(Student student);

    void deleteStudent(Long studentId);

    void updateStudent(Long studentId, String name, String email);
}
