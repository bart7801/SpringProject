package com.sda.uk.javalon1.bart.projects.movies.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    @RequestMapping("registerPage")
    public String registerUserPage() {
        return "registerPage";
    }

    @PostMapping
    @RequestMapping("/registerAction")
    public String saveUser(UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "registerUserSuccess";
    }
}
