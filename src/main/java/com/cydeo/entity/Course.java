package com.cydeo.entity;

import com.cydeo.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    private String name;
    private String description;

    @ManyToOne
    private User courseManager;

    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(columnDefinition = "DATE")
    private LocalDate endDate;

    @OneToMany
    private List<Lesson> lessons;

    @OneToMany
    private Set<Student> students;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

}
