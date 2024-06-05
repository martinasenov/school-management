package com.cydeo.repository;

import com.cydeo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

   Student findByEmail(String email);

}
