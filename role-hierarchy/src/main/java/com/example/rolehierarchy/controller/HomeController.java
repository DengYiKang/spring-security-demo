package com.example.rolehierarchy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class HomeController {
    @GetMapping("/db/**")
    public String db() {
        return "db";
    }

    @GetMapping("/admin/**")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user/**")
    public String user() {
        return "user";
    }
}
