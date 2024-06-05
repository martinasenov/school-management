package com.cydeo.dto;

import com.cydeo.entity.Course;
import com.cydeo.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LessonDTO {

    private Long id;
    private String name;
    private String description;
    private UserDTO instructor;
    private CourseDTO course;


}
