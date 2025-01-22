package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.Project;
import com.portfolio.sarvech.services.ProjectService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/add")
    private String addProject(Model model){
        model.addAttribute("project", new Project());
        return "admin/add-project";
    }

    @PostMapping("/save")
    private String saveProject(@ModelAttribute("project") Project project, List<MultipartFile> files, HttpSession session){
        // save project to database
       this.projectService.saveProject(project, files);
       session.setAttribute("message", new Message("Project added successfully!", MessageType.SUCCESS));
       this.logger.info("Project saved");
       return "redirect:/admin/dashboard";
    }
}
