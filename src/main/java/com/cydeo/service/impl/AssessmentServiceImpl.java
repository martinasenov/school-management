package com.cydeo.service.impl;

import com.cydeo.dto.AssessmentDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.StudentDTO;
import com.cydeo.entity.Assessment;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.AssessmentRepository;
import com.cydeo.service.AssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssessmentServiceImpl implements AssessmentService {

   private final AssessmentRepository assessmentRepository;
   private final MapperUtil mapperUtil;

    public AssessmentServiceImpl(AssessmentRepository assessmentRepository, MapperUtil mapperUtil) {
        this.assessmentRepository = assessmentRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public void save(AssessmentDTO assessmentDTO) {
         assessmentRepository.save(mapperUtil.convert(assessmentDTO,new Assessment()));
    }

    @Override
    public List<AssessmentDTO> findAll() {
        return assessmentRepository.findAll().stream()
                .map(assessment -> mapperUtil.convert(assessment,new AssessmentDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public AssessmentDTO findById(Long id) {
        return mapperUtil.convert(assessmentRepository.findById(id),new AssessmentDTO());
    }


    @Override
    public AssessmentDTO findOrCreateAssessment(StudentDTO student, LessonDTO lesson) {
        Assessment assessment = assessmentRepository
                .findByStudentIdAndLessonId(student.getId(), lesson.getId());


        return mapperUtil.convert(assessment, new AssessmentDTO());
    }
}
