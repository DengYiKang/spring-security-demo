package com.example.restful02.controller;

import com.example.restful02.bean.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class HomeController {
    @GetMapping("/login")
    public ResponseBean login() {
        return new ResponseBean(401, "未登录", null);
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }
}
