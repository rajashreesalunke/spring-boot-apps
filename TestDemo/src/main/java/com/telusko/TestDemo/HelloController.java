package com.telusko.TestDemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")

    public String greet(){

        return "Trust  yourself,have a good day";
    }

}
