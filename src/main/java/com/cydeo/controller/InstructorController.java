package com.cydeo.controller;


import com.cydeo.dto.AssessmentDTO;
import com.cydeo.dto.CourseDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.StudentDTO;
import com.cydeo.entity.Assessment;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.Student;
import com.cydeo.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    private final StudentService studentService;
    private final LessonService lessonService;
    private final CourseService courseService;
    private final AssessmentService assessmentService;

    public InstructorController(StudentService studentService, LessonService lessonService, CourseService courseService, AssessmentService assessmentService) {
        this.studentService = studentService;
        this.lessonService = lessonService;
        this.courseService = courseService;
        this.assessmentService = assessmentService;
    }

    @GetMapping("/students")
    public String viewStudentAssessment(Model model) {
        List<Object[]> courses = courseService.findAllCoursesWithStudentsAndAssessments();
        if (courses.isEmpty()) {
            System.out.println("No courses found with students");
        }
        model.addAttribute("courses", courses);
        return "/instructor/general-assessment";
    }

    @GetMapping("/students/{email}/{lessonId}")
    public String assessStudent(@PathVariable String email, @PathVariable Long lessonId, Model model) {
        StudentDTO student = studentService.findByEmail(email);
        LessonDTO lesson = lessonService.findById(lessonId);

        AssessmentDTO existingAssessment = assessmentService.findByStudentEmailAndLessonId(email, lessonId);
        if (existingAssessment == null) {
            existingAssessment = new AssessmentDTO();
        }

        model.addAttribute("student", student);
        model.addAttribute("lesson", lesson);
        model.addAttribute("instructorAssessment", existingAssessment);

        return "/instructor/assess-student";
    }

    @PostMapping("/students/{email}/{lessonId}")
    public String assessAndSave(@Valid @ModelAttribute("instructorAssessment") AssessmentDTO assessment, BindingResult bindingResult, @PathVariable String email, @PathVariable Long lessonId, Model model) {

        if (bindingResult.hasErrors()) {
            StudentDTO student = studentService.findByEmail(email);
            LessonDTO lesson = lessonService.findById(lessonId);
            model.addAttribute("student", student);
            model.addAttribute("lesson", lesson);
            return "/instructor/assess-student";
        }

        assessment.setLesson(lessonService.findById(lessonId));
        assessment.setStudent(studentService.findByEmail(email));
        assessment.setAssessmentDate(LocalDate.now());

        // Save or update the assessment
        assessmentService.saveOrUpdate(assessment);

        return "redirect:/instructor/students";
    }
}
