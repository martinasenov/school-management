package com.cydeo.controller;

import com.cydeo.dto.AssessmentDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.StudentDTO;
import com.cydeo.entity.Address;
import com.cydeo.enums.State;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final UserService userService;


    public StudentController(StudentService studentService, CourseService courseService, UserService userService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createStudent(Model model){

        model.addAttribute("student",new StudentDTO());
        model.addAttribute("students",studentService.findALl());
        model.addAttribute("states", State.values());

        return "/student/student-create";
    }





    @GetMapping("/assign/{username}")
    public String assessStudent(@PathVariable String username, Model model){

        model.addAttribute("student",studentService.findByEmail(username));
        model.addAttribute("instructorAssessment",new AssessmentDTO());
        model.addAttribute("lesson",new LessonDTO());
        model.addAttribute("courses",courseService.findAll());



        return "student/student-courses";
    }






}
