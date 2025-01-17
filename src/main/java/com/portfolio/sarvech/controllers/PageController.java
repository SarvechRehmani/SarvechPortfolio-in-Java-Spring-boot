package com.portfolio.sarvech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping
    public String index() {
        System.out.println("Home page");
        return "index";
    }

    @GetMapping("home")
    public String home() {
        return "redirect:/";
    }

    @GetMapping("admin/sarvech")
    public String adminLogin() {
        return "admin/login";
    }
}
