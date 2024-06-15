package com.cydeo.service;

import com.cydeo.dto.CourseDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.User;

import java.util.List;

public interface LessonService {

    LessonDTO findById(Long id);
    List<LessonDTO> findAll();
    void save(LessonDTO lessonDTO);
    List<Lesson>findByInstructor(User user);
    void delete(Long id);
    void update(LessonDTO lessonDTO);

}
