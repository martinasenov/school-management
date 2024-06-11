package com.cydeo.dto;

import com.cydeo.entity.Lesson;
import com.cydeo.entity.Student;
import com.cydeo.entity.User;
import com.cydeo.enums.CourseStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDTO {

    private Long id;
    private String name;
    private String description;
    private UserDTO courseManager;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<LessonDTO> lessons;
    private List<StudentDTO> students; // List of students enrolled in the course


}
