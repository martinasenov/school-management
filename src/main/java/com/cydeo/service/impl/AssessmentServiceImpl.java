package com.cydeo.service.impl;

import com.cydeo.dto.AssessmentDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.StudentDTO;
import com.cydeo.entity.Assessment;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.Student;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.AssessmentRepository;
import com.cydeo.repository.LessonRepository;
import com.cydeo.repository.StudentRepository;
import com.cydeo.service.AssessmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final MapperUtil mapperUtil;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    public AssessmentServiceImpl(AssessmentRepository assessmentRepository, MapperUtil mapperUtil, StudentRepository studentRepository, LessonRepository lessonRepository) {
        this.assessmentRepository = assessmentRepository;
        this.mapperUtil = mapperUtil;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void save(AssessmentDTO assessmentDTO) {
        assessmentRepository.save(mapperUtil.convert(assessmentDTO, new Assessment()));
    }

    @Override
    public List<AssessmentDTO> findAll() {
        return assessmentRepository.findAll().stream()
                .map(assessment -> mapperUtil.convert(assessment, new AssessmentDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public AssessmentDTO findById(Long id) {
        return mapperUtil.convert(assessmentRepository.findById(id).orElse(null), new AssessmentDTO());
    }

    @Override
    public AssessmentDTO findOrCreateAssessment(StudentDTO student, LessonDTO lesson) {
        Assessment assessment = assessmentRepository
                .findByStudentIdAndLessonId(student.getId(), lesson.getId());

        return mapperUtil.convert(assessment, new AssessmentDTO());
    }

    @Override
    public AssessmentDTO findByStudentEmailAndLessonId(String email, Long lessonId) {
        Student student = studentRepository.findByEmail(email);
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        if (student != null && lesson != null) {
            Assessment assessment = assessmentRepository.findByStudentAndLesson(student, lesson);
            if (assessment != null) {
                return mapperUtil.convert(assessment, new AssessmentDTO());
            }
        }
        return null;
    }

    @Override
    public void saveOrUpdate(AssessmentDTO assessmentDto) {
        Assessment assessment = mapperUtil.convert(assessmentDto, new Assessment());
        if (assessmentDto.getId() != null) {
            Assessment existingAssessment = assessmentRepository.findById(assessmentDto.getId()).orElse(null);
            if (existingAssessment != null) {
                assessment.setId(existingAssessment.getId());
            }
        }
        assessmentRepository.save(assessment);
    }
}
