package com.cydeo.service;

import com.cydeo.dto.AssessmentDTO;

import java.util.List;

public interface AssessmentService {

    void save (AssessmentDTO assessmentDTO);
    List<AssessmentDTO> findAll();
    AssessmentDTO findById(Long id);
}
