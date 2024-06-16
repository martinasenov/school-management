package com.cydeo.controller;

import com.cydeo.dto.AssessmentDTO;
import com.cydeo.dto.CourseDTO;
import com.cydeo.dto.LessonDTO;
import com.cydeo.dto.StudentDTO;
import com.cydeo.entity.Address;
import com.cydeo.entity.Course;
import com.cydeo.entity.Student;
import com.cydeo.enums.State;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.function.BiPredicate;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final UserService userService;
    private final AssessmentService assessmentService;

    public StudentController(StudentService studentService, CourseService courseService, UserService userService, AssessmentService assessmentService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.userService = userService;
        this.assessmentService = assessmentService;
    }

    @GetMapping("/create")
    public String createStudent(Model model){
        model.addAttribute("student", new StudentDTO());
        model.addAttribute("students", studentService.findALl());
        model.addAttribute("states", State.values());
        return "/student/student-create";
    }

    @PostMapping("/create")
    public String insertStudent (@Valid @ModelAttribute("student") StudentDTO studentDTO, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {


            model.addAttribute("students", studentService.findALl());
            model.addAttribute("states", State.values());
            return "/student/student-create";
        }

        studentService.save(studentDTO);
        return "redirect:/student/create";
    }


    @GetMapping("/assign/{username}")
    public String assignStudent(@PathVariable String username, Model model){
        StudentDTO student = studentService.findByEmail(username);
        List<CourseDTO> courses = courseService.findAll();
        model.addAttribute("student", student);
        model.addAttribute("courses", courses);
        model.addAttribute("isEnrolledInCourse", (BiPredicate<Long, Long>) studentService::isEnrolledInCourse);
        return "student/student-courses";
    }

    @PostMapping("/enroll/{email}/{courseId}")
    public String enrollStudent(@PathVariable String email, @PathVariable Long courseId) {
        studentService.enrollStudentInCourse(email, courseId);
        return "redirect:/student/assign/" + email;
    }

    @PostMapping("/drop/{email}/{courseId}")
    public String dropStudent(@PathVariable String email, @PathVariable Long courseId) {
        studentService.dropStudentFromCourse(email, courseId);
        return "redirect:/student/assign/" + email;
    }

    @GetMapping("/update/{studentEmail}")
    public String editStudent(@PathVariable("studentEmail") String email, Model model){

        model.addAttribute("states", State.values());
        model.addAttribute("student",studentService.findByEmail(email));

        return "student/student-update";
    }


    @PostMapping("/update")
    public String updateStudent(@Valid @ModelAttribute("student")StudentDTO studentDTO, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){

            model.addAttribute("states", State.values());
            model.addAttribute("student", studentDTO);

            return "/student/student-update";
        }

        studentService.save(studentDTO);

        return "redirect:/student/create";
    }



    @GetMapping("/delete/{studentEmail}")
    public String deleteUser(@PathVariable("studentEmail") String email, RedirectAttributes redirectAttributes){


        try{
            studentService.delete(email);
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }


        return "redirect:/student/create";
    }


}
