package com.cydeo.service.impl;

import com.cydeo.dto.CourseDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Course;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.User;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.LessonRepository;
import com.cydeo.service.LessonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final MapperUtil mapperUtil;

    public LessonServiceImpl(LessonRepository lessonRepository, MapperUtil mapperUtil) {
        this.lessonRepository = lessonRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public LessonDTO findById(Long id) {
        return mapperUtil.convert(lessonRepository.findById(id),new LessonDTO());
    }

    @Override
    public List<LessonDTO> findAll() {
        return lessonRepository.findAll().stream()
                .filter(lesson -> lesson.getInstructor().getRole().getDescription().equals("Instructor"))
                .map(lesson -> mapperUtil.convert(lesson,new LessonDTO()))
                .collect(Collectors.toList());
    }


    @Override
    public void save(LessonDTO lessonDTO) {
        lessonRepository.save(mapperUtil.convert(lessonDTO,new Lesson()));
    }

    @Override
    public List<Lesson> findByInstructor(User user) {
        return lessonRepository.findAllByInstructor(user);
    }


    @Override
    public void delete(Long id) {
        Lesson lesson=mapperUtil.convert(lessonRepository.findById(id),new Lesson());

        lesson.setInstructor(null);
        lesson.setCourse(null);

        lessonRepository.delete(lesson);
    }

    @Override
    public void update(LessonDTO lessonDTO) {

        Lesson lesson= lessonRepository.findById(lessonDTO.getId()).orElseThrow(()->new IllegalArgumentException("Invalid Id"));

         lesson.setInstructor(mapperUtil.convert(lessonDTO.getInstructor(),new User()));
         lesson.setCourse(mapperUtil.convert(lessonDTO.getCourse(),new Course()));
         lesson.setName(lessonDTO.getName());
         lesson.setDescription(lessonDTO.getDescription());

        lessonRepository.save(lesson);
    }
}
