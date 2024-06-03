package com.cydeo.controller;

import com.cydeo.dto.CourseDTO;
import com.cydeo.service.CourseService;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("course")
public class CourseController {

       private final CourseService courseService;
       private final RoleService roleService;
       private final UserService userService;


    public CourseController(CourseService courseService, RoleService roleService, UserService userService) {
        this.courseService = courseService;
        this.roleService = roleService;
        this.userService = userService;
    }


    @GetMapping("/create")
    public String createCourse(Model model){

        model.addAttribute("course",new CourseDTO());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("managers", userService.findManagers());


        return "/course/course-create";
    }

    @PostMapping("/create")
    public String insertCourse(@ModelAttribute("course") CourseDTO courseDTO, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){

            model.addAttribute("courses", courseService.findAll());
            model.addAttribute("managers", userService.findManagers());

            return "/course/course-create";
        }

        courseService.save(courseDTO);
        
        return "redirect:/course/create";
    }


}
