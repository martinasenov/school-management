package com.cydeo.service.impl;
import com.cydeo.dto.CourseDTO;
import com.cydeo.entity.Course;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CourseRepository;
import com.cydeo.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final MapperUtil mapperUtil;


    public CourseServiceImpl(CourseRepository courseRepository, MapperUtil mapperUtil) {
        this.courseRepository = courseRepository;
        this.mapperUtil = mapperUtil;
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
}
