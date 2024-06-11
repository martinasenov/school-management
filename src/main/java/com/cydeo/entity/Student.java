package com.cydeo.entity;

import com.cydeo.enums.CourseStatus;
import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student extends BaseEntity{

    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    private Address address;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;
}
/*
@Enumerated(EnumType.STRING)
private CourseStatus courseStatus;*/
