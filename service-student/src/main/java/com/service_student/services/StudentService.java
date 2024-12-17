package com.service_student.services;

import com.service_student.dtos.SchoolDto;
import com.service_student.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAll();

    Optional<Student> findById(String id);

    Student create(Student student);

    List<Student> createMultiple(List<Student> studentList);

}
