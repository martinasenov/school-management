package com.cydeo.repository;


import com.cydeo.entity.Lesson;
import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {

    List<Lesson> findAllByInstructor(User user);
}
