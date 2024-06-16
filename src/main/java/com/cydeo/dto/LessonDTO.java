package com.cydeo.dto;

import com.cydeo.entity.Course;
import com.cydeo.entity.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LessonDTO {

    private Long id;

    @NotBlank(message = "{NotBlank.lesson.name}")
    private String name;

    @NotBlank(message = "{NotBlank.lesson.description}")
    private String description;

    @Valid
    @NotNull(message = "{NotNull.lesson.instructor}")
    private UserDTO instructor;

    @Valid
    @NotNull(message = "{NotNull.lesson.course}")
    private CourseDTO course;


}
