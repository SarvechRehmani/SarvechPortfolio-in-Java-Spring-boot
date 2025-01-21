package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.models.Project;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin/project")
public class ProjectController {

    @GetMapping("/add")
    private String addProject(Model model){
        model.addAttribute("project", new Project());
        return "admin/add-project-";
    }

    @PostMapping("/add")
    private String saveProject(@ModelAttribute("project") Project project, List<MultipartFile> files, Session session){
        // save project to database

        return "redirect:/admin/project";
    }
}
