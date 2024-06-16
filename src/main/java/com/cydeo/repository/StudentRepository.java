package com.cydeo.repository;

import com.cydeo.entity.Course;
import com.cydeo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student,Long> {

   Student findByEmail(String email);

   List<Student> findAllByCoursesIsNotNull();

   @Query("SELECT COUNT(c) > 0 FROM Student s JOIN s.courses c WHERE s.email = :email")
   boolean hasCoursesAssigned(@Param("email") String email);

}
