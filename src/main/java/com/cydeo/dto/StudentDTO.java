package com.cydeo.dto;

import com.cydeo.entity.Address;
import com.cydeo.entity.Course;
import com.cydeo.enums.CourseStatus;
import com.cydeo.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {


    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private AddressDTO address;
    private List<CourseDTO> courses; // List of courses the student is enrolled in

}
