package com.cydeo.dto;

import com.cydeo.enums.Gender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {


    private Long id;

    @NotEmpty(message = "{NotEmpty.student.firstName}")
    private String firstName;

    @NotEmpty(message = "{NotEmpty.student.lastName}")
    private String lastName;

    @NotEmpty(message = "{NotEmpty.student.email}")
    private String email;

    @NotNull(message = "{NotNull.student.gender}")
    private Gender gender;

    @Valid
    @NotNull(message = "{NotNull.student.address}")
    private AddressDTO address;

    private CourseDTO course;

}
