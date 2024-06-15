package com.cydeo.service;


import com.cydeo.dto.CourseDTO;
import com.cydeo.dto.StudentDTO;
import com.cydeo.entity.Student;

import java.util.List;

public interface StudentService {

   StudentDTO findById(Long id);
   List<StudentDTO> findALl();
   void save(StudentDTO studentDTO);
   StudentDTO findByEmail(String email);
   void enrollStudentInCourse(String email, Long courseId);
   void dropStudentFromCourse(String email, Long courseId);
   boolean isEnrolledInCourse(Long studentId, Long courseId);
   void update(StudentDTO studentDTO);
   void delete(String email);
}
