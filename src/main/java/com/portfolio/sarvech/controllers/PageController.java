package com.portfolio.sarvech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("nn","fa-brands fa-pinterest-p");
        System.out.println("Home page");
        return "index";
    }

    @GetMapping("home")
    public String home() {
        return "redirect:/";
    }

    @GetMapping("/login/admin-sarvech")
    public String adminLogin() {

        return "login";
    }
}
