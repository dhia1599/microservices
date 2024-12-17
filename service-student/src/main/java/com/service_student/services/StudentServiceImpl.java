package com.service_student.services;

import com.service_student.dtos.SchoolDto;
import com.service_student.entities.Student;
import com.service_student.repositories.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private final StudentRepo studentRepo;

    private final RestTemplate restTemplate;

    private final String SCHOOL_API_URL = "http://service-school/api/schools/";
    public StudentServiceImpl(StudentRepo studentRepo, RestTemplate restTemplate) {
        this.studentRepo = studentRepo;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = this.studentRepo.findAll();

        // Parcourir chaque étudiant pour enrichir avec les informations de l'école
        students.forEach(student -> {
            // Appel au service des écoles pour récupérer les informations de l'école
            //String schoolApiUrl = "http://localhost:8081/api/schools/" + student.getSchoolId();
            String schoolApiUrl = SCHOOL_API_URL + student.getSchoolId();
            SchoolDto schoolDto = restTemplate.getForObject(schoolApiUrl, SchoolDto.class);

            // Mettre à jour l'attribut school de l'étudiant
            student.setSchool(schoolDto);
        });

        return students;
    }

    @Override
    public Optional<Student> findById(String id) {
        //récupérer l'étudiant
        Optional<Student> optionalStudent = this.studentRepo.findById(id);

        //si student existe récupérer les infos de son ecole
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            // Appel au service des écoles pour récupérer les informations
            //String schoolApiUrl = "http://localhost:8081/api/schools/" + student.getSchoolId();
            String schoolApiUrl = SCHOOL_API_URL + student.getSchoolId();
            SchoolDto schoolDto = restTemplate.getForObject(schoolApiUrl, SchoolDto.class);

            //set de l'école pour le student
            student.setSchool(schoolDto);
        }
        return optionalStudent;
    }

    @Override
    public Student create(Student student) {
        return this.studentRepo.save(student);
    }

    @Override
    public List<Student> createMultiple(List<Student> studentList) {
        return this.studentRepo.saveAll(studentList);
    }

}
