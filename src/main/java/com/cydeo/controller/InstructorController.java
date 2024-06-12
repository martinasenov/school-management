package com.cydeo.controller;


import com.cydeo.dto.AssessmentDTO;
import com.cydeo.dto.CourseDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.StudentDTO;
import com.cydeo.entity.Assessment;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.Student;
import com.cydeo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<Object[]> courses = courseService.findAllCoursesWithStudents();
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

        // Fetch or create a new assessment
        AssessmentDTO assessment = assessmentService.findOrCreateAssessment(student, lesson);

        model.addAttribute("student", student);
        model.addAttribute("lesson", lesson);
        model.addAttribute("assessment", assessment);
        return "/instructor/assess-student";
    }

  /*  @GetMapping("/lessons/{lessonId}/students")
    public String viewStudentsInLesson(@PathVariable Long lessonId, Model model) {
        LessonDTO lesson = lessonService.findById(lessonId);
        List<Student> students = (List<Student>) lesson.getCourse().getStudents();
        model.addAttribute("lesson", lesson);
        model.addAttribute("students", students);
        return "/instructor/general-assessment";
    }
*/
}
