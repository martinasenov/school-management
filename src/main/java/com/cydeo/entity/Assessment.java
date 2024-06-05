package com.cydeo.entity;

import com.cydeo.dto.StudentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assessments")
public class Assessment extends BaseEntity {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> student;

    @ManyToOne
    private Lesson lesson;

    private String grade;

    @Column(columnDefinition = "DATE")
    private LocalDate date;

    private String instructorImpressionOfStudent;
}