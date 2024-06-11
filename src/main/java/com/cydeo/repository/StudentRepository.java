package com.cydeo.repository;

import com.cydeo.entity.Course;
import com.cydeo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

   Student findByEmail(String email);

   List<Student> findAllByCoursesIsNotNull();


}
