package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.Project;
import com.portfolio.sarvech.services.ProjectService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Binding;
import java.util.List;

@Controller
@RequestMapping("/admin/project")
public class ProjectController {

    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/view/{id}")
    public String getProjectById(@PathVariable long id, Model model, HttpSession session){
        Project project = this.projectService.findProjectById(id);
        if (project == null){
            session.setAttribute("message",new Message("Project not found", MessageType.ERROR));
            return "/admin/dashboard";
        }
        model.addAttribute("project",project);
        return "/admin/view-project";
    }

    @GetMapping("/self/add")
    private String addProject(Model model){
        model.addAttribute("project", new Project("self"));
        return "/admin/add-project";
    }

    @GetMapping("/client/add")
    private String addClientProject(Model model){
        model.addAttribute("project", new Project());
        return "admin/add-project";
    }

    @PostMapping("/save")
    private String saveProject(@Valid Project project, BindingResult result, List<MultipartFile> files, HttpSession session){

        if(result.hasErrors()){
            session.setAttribute("message",new Message("Please fix the following errors",MessageType.ERROR));
            return "/admin/add-project";
        }

        // Log received files
        if (files == null || files.isEmpty() || files.stream().allMatch(MultipartFile::isEmpty)) {
            session.setAttribute("message",new Message("Please fix the following errors",MessageType.ERROR));
            session.setAttribute("fileMessage", "Please select at least one valid file.");
            return "/admin/add-project";
        }

        // save project to database
       this.projectService.saveProject(project, files);
       session.setAttribute("message", new Message("Project added successfully!", MessageType.SUCCESS));
       this.logger.info("Project saved");
       return "redirect:/admin/dashboard";
    }

    @GetMapping("/update/{id}")
    public String editProject(@PathVariable long id, Model model, HttpSession session){
        Project project = this.projectService.findProjectById(id);
        if (project == null){
            session.setAttribute("message", new Message("Project not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard#projects";
        }
        model.addAttribute("project", project);
        return "/admin/edit-project";
    }

    @PostMapping("/update/{id}")
    public String updateProject(@Valid Project project,BindingResult result,  @PathVariable long id, HttpSession session){
        if (result.hasErrors()){
            session.setAttribute("message", new Message("Please Fix the following errors", MessageType.ERROR));
            return "/admin/edit-project";
        }
        Project existingProject = this.projectService.findProjectById(id);
        if (existingProject == null){
            session.setAttribute("message", new Message("Project not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }



        project.setImages(existingProject.getImages());
        this.projectService.updateProject(project);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable long id, HttpSession session){
        Project project = this.projectService.findProjectById(id);
        if(project == null){
            logger.error("Project not found with id: {}", id);
            session.setAttribute("message", new Message("Project not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        this.projectService.deleteProjectById(id);
        session.setAttribute("message", new Message("Project deleted successfully!", MessageType.SUCCESS));
        this.logger.info("Project deleted: {}", project);
        return "redirect:/admin/dashboard";
    }
}
