package com.cydeo.service;

import com.cydeo.dto.AssessmentDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.StudentDTO;

import java.util.List;

public interface AssessmentService {

    void save (AssessmentDTO assessmentDTO);
    List<AssessmentDTO> findAll();
    AssessmentDTO findById(Long id);


    AssessmentDTO findOrCreateAssessment(StudentDTO student, LessonDTO lesson);

}
