package com.cydeo.controller;


import com.cydeo.dto.AssessmentDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.StudentDTO;
import com.cydeo.entity.Assessment;
import com.cydeo.entity.Lesson;
import com.cydeo.entity.Student;
import com.cydeo.service.AssessmentService;
import com.cydeo.service.LessonService;
import com.cydeo.service.StudentService;
import com.cydeo.service.UserService;
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


    public InstructorController(StudentService studentService, LessonService lessonService) {
        this.studentService = studentService;
        this.lessonService = lessonService;
    }

    @GetMapping("/students")
    public String viewStudentAssessment(Model model) {


        model.addAttribute("students", studentService.findAllByCoursesIsNotNull());

        return "/instructor/general-assessment";
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
