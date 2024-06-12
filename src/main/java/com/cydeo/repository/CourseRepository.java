package com.cydeo.repository;

import com.cydeo.entity.Course;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {


    List<Course> findAllByStudentsIsNotNull();

    List<Course> findByCourseManager(User courseManager);



}
