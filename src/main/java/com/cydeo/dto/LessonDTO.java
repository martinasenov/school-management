package com.cydeo.dto;

import com.cydeo.entity.Course;
import com.cydeo.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LessonDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private UserDTO instructor;

    @NotNull
    private CourseDTO course;


}
