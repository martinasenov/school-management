package com.cydeo.service.impl;

import com.cydeo.dto.StudentDTO;
import com.cydeo.entity.Student;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.StudentRepository;
import com.cydeo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final MapperUtil mapperUtil;

    public StudentServiceImpl(StudentRepository studentRepository, MapperUtil mapperUtil) {
        this.studentRepository = studentRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public StudentDTO findById(Long id) {
        return mapperUtil.convert(studentRepository.findById(id),new StudentDTO());
    }

    @Override
    public List<StudentDTO> findALl() {
        return studentRepository.findAll()
                .stream()
                .map(student -> mapperUtil.convert(student,new StudentDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public void save(StudentDTO studentDTO) {
        studentRepository.save(mapperUtil.convert(studentDTO,new Student()));
    }

    @Override
    public StudentDTO findByEmail(String email) {
        return mapperUtil.convert(studentRepository.findByEmail(email),new StudentDTO());
    }
}
