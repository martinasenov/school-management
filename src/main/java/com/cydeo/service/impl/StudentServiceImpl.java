package com.cydeo.service.impl;

import com.cydeo.dto.CourseDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.StudentDTO;
import com.cydeo.entity.Address;
import com.cydeo.entity.Course;
import com.cydeo.entity.Student;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.AddressRepository;
import com.cydeo.repository.CourseRepository;
import com.cydeo.repository.StudentRepository;
import com.cydeo.service.CourseService;
import com.cydeo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final MapperUtil mapperUtil;
    private final CourseService courseService;
    private final CourseRepository courseRepository;
    private final AddressRepository addressRepository;

    public StudentServiceImpl(StudentRepository studentRepository, MapperUtil mapperUtil, CourseService courseService, CourseRepository courseRepository, AddressRepository addressRepository) {
        this.studentRepository = studentRepository;
        this.mapperUtil = mapperUtil;
        this.courseService = courseService;
        this.courseRepository = courseRepository;
        this.addressRepository = addressRepository;
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
        Address address=mapperUtil.convert(studentDTO.getAddress(),new Address());
        Student student=mapperUtil.convert(studentDTO,new Student());

        addressRepository.save(address);

        student.setAddress(address);

        studentRepository.save(student);
    }

    @Override
    public StudentDTO findByEmail(String email) {
        return mapperUtil.convert(studentRepository.findByEmail(email),new StudentDTO());
    }


    @Override
    public void addToCourseList(Long courseId,String username) {

        Student student = studentRepository.findByEmail(username);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Invalid course ID"));
        student.getCourses().add(course);
        studentRepository.save(student);

    }




    @Override
    public void enrollStudentInCourse(String email, Long courseId) {
        Student student = studentRepository.findByEmail(email);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }


    @Override
    public void dropStudentFromCourse(String email, Long courseId) {
        Student student = studentRepository.findByEmail(email);
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));

        student.getCourses().remove(course);
        course.getStudents().remove(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }


    @Override
    public boolean isEnrolledInCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return student.getCourses().contains(course);
    }

    // Implement the findAllCourses method




    @Override
    public void update(StudentDTO studentDTO) {

        Student student=studentRepository.findByEmail(studentDTO.getEmail());
        student.getCourses().add(mapperUtil.convert(studentDTO.getCourseDTO(),new Course()));

        studentRepository.save(student);
    }


}
