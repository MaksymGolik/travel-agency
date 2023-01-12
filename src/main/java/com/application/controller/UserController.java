package com.application.controller;

import com.application.dto.UserCreateRequest;
import com.application.dto.UserResponse;
import com.application.dto.mapper.UserCreateRequestMapper;
import com.application.dto.mapper.UserResponseMapper;
import com.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String test(){
        return "test_page";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(Principal principal, Model model){
        UserResponse userResponse = UserResponseMapper.mapToDto(userService.readByEmail(principal.getName()));
        model.addAttribute("userResponse", userResponse);
        return "user-info";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new UserCreateRequest());
        return "create-user";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("user") UserCreateRequest user, BindingResult result) {
        if (result.hasErrors()) {
            return "create-user";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(UserCreateRequestMapper.mapToModel(user));
        return "redirect:/users/login";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/all")
    public String all(Model model){
        model.addAttribute("users", userService.readAll().stream()
                .map(UserResponseMapper::mapToDto).collect(Collectors.toList()));
        return "users-list";
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/profile/{id}")
    public String profile(@PathVariable long id, Model model){
        model.addAttribute(UserResponseMapper.mapToDto(userService.readById(id)));
        return "user-info";
    }

    @GetMapping("/denied")
    public String profile(){
        return "access-denied";
    }
}
