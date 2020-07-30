package com.example.securitywithdb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class HomeController {
    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) auth.getAuthorities();
        model.addAttribute("authorities", authorities.toString());
        model.addAttribute("username", auth.getName());
        return "index";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }
}
