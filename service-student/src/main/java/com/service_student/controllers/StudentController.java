package com.service_student.controllers;

import com.service_student.dtos.SchoolDto;
import com.service_student.entities.Student;
import com.service_student.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAll(){
        return this.studentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable String id) {
        Student student = this.studentService.findById(id).orElse(null);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return this.studentService.create(student);
    }

    @PostMapping("/bulk")
    public List<Student> createMultiple(@RequestBody List<Student> studentList) {
        return this.studentService.createMultiple(studentList);
    }

}
