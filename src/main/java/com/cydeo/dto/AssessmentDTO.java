package com.cydeo.dto;

import com.cydeo.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssessmentDTO {

    private Long id;
    private StudentDTO student;
    private LessonDTO lesson;
    private LocalDate assessmentDate;
    private String grade;
    private String instructorImpressionOfStudent;


}
