package com.cydeo.controller;


import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.enums.State;
import com.cydeo.repository.CourseRepository;
import com.cydeo.repository.LessonRepository;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;;
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;


    public UserController(UserService userService, RoleService roleService, UserRepository userRepository, LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/create")
    public String userCreate(Model model) {

        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("states", State.values());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", new UserDTO());

        return "user/user-create";
    }

    @PostMapping("/create")
    public String insertUser(@Valid @ModelAttribute("user") UserDTO userdto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("states", State.values());
            model.addAttribute("users", userService.findAll());

            return "user/user-create";
        }

        userService.save(userdto);

        return "redirect:/user/create";
    }

    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username,Model model){

        model.addAttribute("user", userService.findByUsername(username));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("states", State.values());

        return "user/user-update";
    }


    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model){

        User existingUser = userRepository.findByUserName(userDTO.getUserName());

        // Check if the user's role change is valid
        if (!existingUser.getRole().getId().equals(userDTO.getRole().getId())) {
            boolean hasAssignedLessons = !lessonRepository.findAllByInstructor(existingUser).isEmpty();
            boolean hasAssignedCourses = !courseRepository.findByCourseManager(existingUser).isEmpty();

            if (hasAssignedLessons) {
                bindingResult.rejectValue("role", "error.user.lessonAssigned", "Cannot change role because the user is assigned to lessons.");
            }
            if (hasAssignedCourses) {
                bindingResult.rejectValue("role", "error.user.courseAssigned", "Cannot change role because the user is assigned to courses.");
            }
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("states", State.values());
            return "user/user-update";
        }

        userService.update(userDTO);
        return "redirect:/user/create";
    }


    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username, RedirectAttributes redirectAttributes) {

        try {
            userService.delete(username);
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/user/create";
    }
}
