package com.cydeo.dto;

import com.cydeo.entity.Lesson;
import com.cydeo.entity.Student;
import com.cydeo.entity.User;
import com.cydeo.enums.CourseStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDTO {

    private Long id;

    @NotEmpty(message = "{NotEmpty.course.name}")
    private String name;

    @NotEmpty(message = "{NotEmpty.course.description}")
    private String description;

    @Valid
    @NotNull(message = "{NotNull.course.courseManager}")
    private UserDTO courseManager;

    @NotNull(message = "{NotNull.course.startDate}")
    private LocalDate startDate;

    @NotNull(message = "{NotNull.course.endDate}")
    private LocalDate endDate;


    private List<LessonDTO> lessonDTOs;
    private List<StudentDTO> studentDTOs;


}
