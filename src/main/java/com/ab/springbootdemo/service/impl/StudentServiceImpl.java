package com.ab.springbootdemo.service.impl;

import com.ab.springbootdemo.entity.Student;
import com.ab.springbootdemo.repository.StudentRepository;
import com.ab.springbootdemo.service.StudentService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student) {

        Optional<Student> studentByGivenEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByGivenEmail.isPresent()) {
            throw new IllegalStateException("email is taken");
        }

        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) {

        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id : " + studentId + " does not exists");
        }

        studentRepository.deleteById(studentId);

    }

    @Override
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {

        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new IllegalStateException("student with id : " + studentId + " does not exists"));
        if (name != null && name.length() != 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() != 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> exists = studentRepository.findStudentByEmail(email);

            if (exists.isPresent()) {
                throw new IllegalStateException("email : " + email + " is taken.");
            }
            student.setEmail(email);
        }
    }
}
