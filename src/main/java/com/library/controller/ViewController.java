package com.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {


    @GetMapping("/")
    public String home(){

        return "home";
    }


    @GetMapping("/login-page")
    public String loginPage(){

        return "login";
    }


    @GetMapping("/register-page")
    public String registerPage(){

        return "register";
    }


    @GetMapping("/dashboard-page")
    public String dashboard(){

        return "dashboard";
    }


    @GetMapping("/manage-books")
    public String books(){

        return "books";
    }


    @GetMapping("/manage-students")
    public String students(){

        return "students";
    }


    @GetMapping("/issue-page")
    public String issueBook(){

        return "issuebook";
    }


    @GetMapping("/return-page")
    public String returnBook(){

        return "returnbook";
    }

}