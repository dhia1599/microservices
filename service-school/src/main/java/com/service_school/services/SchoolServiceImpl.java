package com.service_school.services;

import com.service_school.entities.School;
import com.service_school.repositories.SchoolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService{

    @Autowired
    private final SchoolRepo schoolRepo;

    public SchoolServiceImpl(SchoolRepo schoolRepo) {
        this.schoolRepo = schoolRepo;
    }

    @Override
    public List<School> getAll() {
        return this.schoolRepo.findAll();
    }

    @Override
    public Optional<School> getById(Long id) {
        return this.schoolRepo.findById(id);
    }

    @Override
    public School create(School school) {
        return this.schoolRepo.save(school);
    }

    @Override
    public List<School> createMultiple(List<School> schoolList) {
        return this.schoolRepo.saveAll(schoolList);
    }

    @Override
    public void delete(Long id) {
        this.schoolRepo.deleteById(id);
    }
}
