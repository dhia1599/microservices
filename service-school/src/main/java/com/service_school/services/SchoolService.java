package com.service_school.services;


import com.service_school.entities.School;

import java.util.List;
import java.util.Optional;

public interface SchoolService {
    List<School> getAll();

    Optional<School> getById(Long id);

    School create(School school);

    List<School> createMultiple(List<School> schoolList);

    void delete(Long id);
}
