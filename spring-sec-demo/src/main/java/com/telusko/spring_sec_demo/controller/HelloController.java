package com.telusko.spring_sec_demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/home")

    public String greet(HttpServletRequest request){

        return "hello rajashree"+request.getSession().getId();

    }
    @GetMapping("/about")
    public String about(HttpServletRequest request){
        return " rajashree"+request.getSession().getId();

    }
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";  // this will render templates/login.html
//    }
//
//    @GetMapping("/register-form")
//    public String registerPage() {
//        return "register";  // renders templates/register.html
//    }
//
//    @GetMapping("/dashboard")
//    public String dashboard() {
//        return "dashboard";  // renders templates/dashboard.html
//    }



}
