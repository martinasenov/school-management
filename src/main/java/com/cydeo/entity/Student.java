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

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany(mappedBy = "students",cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Course> courses;
}
