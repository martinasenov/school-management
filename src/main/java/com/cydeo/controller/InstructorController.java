package com.cydeo.controller;


import com.cydeo.dto.AssessmentDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.entity.Assessment;
import com.cydeo.service.AssessmentService;
import com.cydeo.service.LessonService;
import com.cydeo.service.StudentService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class InstructorController {

    private final UserService userService;
    private final StudentService studentService;
    private final LessonService lessonService;
    private final AssessmentService assessmentService;

    public InstructorController(UserService userService, StudentService studentService, LessonService lessonService, AssessmentService assessmentService) {
        this.userService = userService;
        this.studentService = studentService;
        this.lessonService = lessonService;
        this.assessmentService = assessmentService;
    }



}
