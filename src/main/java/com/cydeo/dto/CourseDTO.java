package com.cydeo.dto;

import com.cydeo.entity.Lesson;
import com.cydeo.entity.Student;
import com.cydeo.entity.User;
import com.cydeo.enums.CourseStatus;
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

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    private UserDTO courseManager;

    @NotEmpty
    private LocalDate startDate;

    @NotEmpty
    private LocalDate endDate;


    private List<LessonDTO> lessonDTOs;
    private List<StudentDTO> studentDTOs;


}
