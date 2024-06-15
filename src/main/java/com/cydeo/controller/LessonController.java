package com.cydeo.controller;

import com.cydeo.dto.LessonDTO;
import com.cydeo.service.CourseService;
import com.cydeo.service.LessonService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/update/{lessonId}")
    public String editLesson(@PathVariable("lessonId") Long id ,Model model){

        model.addAttribute("courses",courseService.findAll());
        model.addAttribute("instructors",userService.findInstructors());
        model.addAttribute("lesson",lessonService.findById(id));


        return "lesson/lesson-update";
    }

    @PostMapping("/update")
    public String updateLesson(@ModelAttribute("lesson") LessonDTO lessonDTO,BindingResult bindingResult,Model model){


        if (bindingResult.hasErrors()){

            model.addAttribute("instructors",userService.findInstructors());

            return "lesson/lesson-update";
        }

        lessonService.update(lessonDTO);

        return "redirect:/lesson/create";
    }


    @GetMapping("/delete/{lessonId}")
    public String deleteLesson(@PathVariable("lessonId") Long id){

        lessonService.delete(id);

        return "redirect:/lesson/create";
    }
}
