package com.service_student.entities;

import com.service_student.dtos.SchoolDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
public class Student {

    @Id
    @Indexed(unique = true)
    private String id;
    private String name;
    private int age;
    private String gender;
    private Long schoolId;
    private SchoolDto school;

}
