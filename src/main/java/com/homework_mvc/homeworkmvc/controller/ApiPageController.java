package com.homework_mvc.homeworkmvc.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apiPage")
public class ApiPageController {

    @GetMapping
    private String getApiPage(Model model){
        return "api-page";
    }

}
