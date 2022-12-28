package com.application.controller;

import com.application.dto.UserCreateRequest;
import com.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    IUserService userService;

    @GetMapping("/test")
    public String test(){
        userService.saveUser(new UserCreateRequest("ivan123@gmail.com","123qwerty","Ivan","Ivanow","+38099554"));
        return "home_page";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "home_page";
    }
}
