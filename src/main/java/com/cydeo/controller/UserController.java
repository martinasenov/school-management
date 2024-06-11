package com.cydeo.controller;


import com.cydeo.dto.UserDTO;
import com.cydeo.enums.State;
import com.cydeo.service.AddressService;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final AddressService addressService;
    private final RoleService roleService;

    public UserController(UserService userService, AddressService addressService, RoleService roleService) {
        this.userService = userService;
        this.addressService = addressService;
        this.roleService = roleService;
    }

    @GetMapping("/create")
    public String userCreate(Model model) {

        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("states", State.values());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", new UserDTO());


        return "/user/user-create";
    }

    @PostMapping("/create")
    public String insertUser(@ModelAttribute("user") UserDTO userdto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("states", State.values());
            model.addAttribute("users", userService.findAll());

            return "/user/user-create";

        }

        userService.save(userdto);

        return "redirect:/user/create";

    }

    @GetMapping("/update/{username}")
    public String editUser(@PathVariable("username") String username,Model model){

        model.addAttribute("user", userService.findByUsername(username));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("states", State.values());

        return "/user/user-update";
    }


    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {

            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("states", State.values());

            return "/user/user-update";

        }

        userService.update(userDTO);
        return "redirect:/user/create";
    }


}
