package com.service_school.controllers;

import com.service_school.entities.School;
import com.service_school.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public List<School> getAll(){
        return this.schoolService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getById(@PathVariable Long id) {
        School school = this.schoolService.getById(id).orElse(null);
        return school != null ? ResponseEntity.ok(school) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public School create(@RequestBody School school) {
        return this.schoolService.create(school);
    }

    @PostMapping("/bulk")
    public List<School> createMultiple (@RequestBody List<School> schoolList) {
        return this.schoolService.createMultiple(schoolList);
    }

    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            this.schoolService.delete(id);
            return ResponseEntity.ok("School deleted");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("school not found !");
        }
    }
}
