package com.cydeo.service;

import com.cydeo.dto.CourseDTO;
import com.cydeo.entity.Course;

import java.util.List;

public interface CourseService {

    List<CourseDTO> findAll();
    CourseDTO findById(Long id);
    void save(CourseDTO courseDTO);
}
