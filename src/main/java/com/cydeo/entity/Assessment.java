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

    @ManyToOne
    private Student student;

    @ManyToOne
    private Lesson lesson;

    @Column(columnDefinition = "DATE")
    private LocalDate date;

    private String grade;

    private String instructorImpressionOfStudent;


}