package com.cydeo.controller;


import com.cydeo.service.AssessmentService;
import com.cydeo.service.LessonService;
import com.cydeo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


public class AssessmentController {
    private final AssessmentService assessmentService;
    private final StudentService studentService;
    private final LessonService lessonService;

    public AssessmentController(AssessmentService assessmentService, StudentService studentService, LessonService lessonService) {
        this.assessmentService = assessmentService;
        this.studentService = studentService;
        this.lessonService = lessonService;
    }






}
