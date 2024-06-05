package com.cydeo.controller;

import com.cydeo.dto.LessonDTO;
import com.cydeo.service.CourseService;
import com.cydeo.service.LessonService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lesson")
public class LessonController {

    private final CourseService courseService;
    private final UserService userService;
    private final LessonService lessonService;

    public LessonController(CourseService courseService, UserService userService, LessonService lessonService) {
        this.courseService = courseService;
        this.userService = userService;
        this.lessonService = lessonService;
    }

    @GetMapping("/create")
    public String lessonCreate(Model model){

        model.addAttribute("lesson",new LessonDTO());
        model.addAttribute("courses",courseService.findAll());
        model.addAttribute("instructors",userService.findInstructors());
        model.addAttribute("lessons",lessonService.findAll());

        return "/lesson/lesson-create";
    }

    @PostMapping("/create")
    public String insertLesson(@ModelAttribute("lesson") LessonDTO lessonDTO, BindingResult bindingResult,Model model){


        if (bindingResult.hasErrors()){

            model.addAttribute("courses",courseService.findAll());
            model.addAttribute("instructors",userService.findInstructors());
            model.addAttribute("lessons",lessonService.findAll());

            return "/lesson/lesson-create";
        }

        lessonService.save(lessonDTO);

        return "redirect:/lesson/create";
    }




}
