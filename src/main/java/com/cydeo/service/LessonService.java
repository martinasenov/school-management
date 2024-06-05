package com.cydeo.service;

import com.cydeo.dto.LessonDTO;
import com.cydeo.entity.Lesson;

import java.util.List;

public interface LessonService {

    Lesson findById(Long id);
    List<LessonDTO> findAll();
    void save(LessonDTO lessonDTO);

}
