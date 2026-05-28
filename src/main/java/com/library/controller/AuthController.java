package com.library.controller;

import org.springframework.web.bind.annotation.*;

import com.library.dto.LoginRequest;
import com.library.dto.RegisterRequest;
import com.library.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    public AuthController(
            UserService userService){

        this.userService=
                userService;
    }

    @PostMapping("/register")
    public String register(
    @RequestBody RegisterRequest request){

        return userService
                .register(request);
    }

    @PostMapping("/login")
    public String login(
    @RequestBody LoginRequest request){

        return userService
                .login(request);
    }
}