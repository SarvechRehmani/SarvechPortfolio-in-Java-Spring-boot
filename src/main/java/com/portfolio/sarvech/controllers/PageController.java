package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.AllDataResponse;
import com.portfolio.sarvech.models.ContactForm;
import com.portfolio.sarvech.models.Details;
import com.portfolio.sarvech.models.Project;
import com.portfolio.sarvech.services.DetailsService;
import com.portfolio.sarvech.services.LoadAllDataService;
import com.portfolio.sarvech.services.SocialLinkService;
import com.portfolio.sarvech.services.implementations.LoadAllDataServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    private final Logger logger = LoggerFactory.getLogger(PageController.class);

    private final LoadAllDataService loadAllDataService;

    public PageController(LoadAllDataService loadAllDataService) {
        this.loadAllDataService = loadAllDataService;

    }

    @GetMapping
    public String index(Model model) {
        this.logger.info("Index page.");
        AllDataResponse data = this.loadAllDataService.loadAllData();
        if(data.getDetails() == null){
            return "/maintenance";
        }
        model.addAttribute("data",data);
        model.addAttribute("contactForm", new ContactForm());
        return "index";
    }

    @GetMapping("home")
    public String home() {
        return "redirect:/";
    }

    @GetMapping("/login/admin-sarvech")
    public String adminLogin(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/admin/dashboard";
        }
        return "login";
    }

    @GetMapping("/project/{id}")
    public String projectDetails(@PathVariable  Long id, Model model, HttpSession session) {
        Project project = this.loadAllDataService.getProjectById(id);
        if(project == null) {
            session.setAttribute("message", "Project not found");
            return "redirect:/#portfolio";
        }
        AllDataResponse data = this.loadAllDataService.loadAllData();
        model.addAttribute("data", data);
        model.addAttribute("project", project);
        return "view-project";
    }
}
