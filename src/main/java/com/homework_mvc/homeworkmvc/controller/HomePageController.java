package com.homework_mvc.homeworkmvc.controller;

import com.homework_mvc.homeworkmvc.dto.UserDto;
import com.homework_mvc.homeworkmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class HomePageController {


    private UserService userService;

    @Autowired
    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    private String listUsers(Model model){
        List<UserDto> users = userService.findAll();
        model.addAttribute("users",users);
        return "home";
    }

}
