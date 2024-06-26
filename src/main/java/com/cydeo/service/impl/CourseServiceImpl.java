package com.cydeo.service.impl;
import com.cydeo.dto.CourseDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.StudentDTO;
import com.cydeo.entity.*;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CourseRepository;
import com.cydeo.repository.StudentRepository;
import com.cydeo.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final MapperUtil mapperUtil;
    private final StudentRepository studentRepository;


    public CourseServiceImpl(CourseRepository courseRepository, MapperUtil mapperUtil, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.mapperUtil = mapperUtil;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<CourseDTO> findAll() {
        return courseRepository.findAll().stream()
                .map(course->mapperUtil.convert(course,new CourseDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDTO findById(Long id) {
        return mapperUtil.convert(courseRepository.findById(id),new CourseDTO());
    }

    @Override
    public void save(CourseDTO courseDTO) {
        courseRepository.save(mapperUtil.convert(courseDTO,new Course()));
    }

    @Override
    public List<Object[]> findAllCoursesWithStudentsAndAssessments() {
        List<Object[]> result = new ArrayList<>();
        List<Student> students = studentRepository.findAll();

        for (Student student : students) {

            for (Course course : student.getCourses()) {

                for (Lesson lesson : course.getLessons()) {

                    List<Assessment> assessments = lesson.getAssessments();

                    if (assessments != null && !assessments.isEmpty()) {

                        for (Assessment assessment : assessments) {

                            if (assessment.getStudent().getId().equals(student.getId())) {

                                result.add(new Object[]{student, lesson, assessment});
                            }
                        }

                    } else {

                        result.add(new Object[]{student, lesson, null});
                    }
                }
            }
        }

        return result;
    }


    @Override
    public void update(CourseDTO courseDTO) {

        // Ensure courseDTO and its fields are not null
        if (courseDTO == null || courseDTO.getId() == null) {
            throw new IllegalArgumentException("CourseDTO and its ID cannot be null");
        }

        // Find the existing course entity
        Course existingCourse = courseRepository.findById(courseDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid course Id: " + courseDTO.getId()));

        // Update fields only if they are not null
        if (courseDTO.getCourseManager() != null) {
            existingCourse.setCourseManager(mapperUtil.convert(courseDTO.getCourseManager(), new User()));
        }
        if (courseDTO.getName() != null) {
            existingCourse.setName(courseDTO.getName());
        }
        if (courseDTO.getStartDate() != null) {
            existingCourse.setStartDate(courseDTO.getStartDate());
        }
        if (courseDTO.getEndDate() != null) {
            existingCourse.setEndDate(courseDTO.getEndDate());
        }

        // Clear existing lessons and add new ones if they are not null
        if (courseDTO.getLessonDTOs() != null) {
            existingCourse.getLessons().clear();
            existingCourse.getLessons().addAll(mapperUtil.convert(courseDTO.getLessonDTOs(), new ArrayList<>()));
        }

        // Save the updated course
        courseRepository.save(existingCourse);
    }


    @Override
    public void delete(Long id) {

        Course course=mapperUtil.convert(courseRepository.findById(id),new Course());

        course.setCourseManager(null);
        course.getStudents().clear();
        courseRepository.delete(course);

    }
}
