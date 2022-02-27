package com.ab.springbootdemo.controller;

import com.ab.springbootdemo.entity.Student;
import com.ab.springbootdemo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("/{studentId}")
    public void updateStudent(@PathVariable Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {

        studentService.updateStudent(studentId, name, email);

    }

}
