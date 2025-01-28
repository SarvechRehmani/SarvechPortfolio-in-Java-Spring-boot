package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.*;
import com.portfolio.sarvech.services.*;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.model.IModel;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final DetailsService detailsService;
    private final SocialLinkService socialLinkService;
    private final ProjectService projectService;
    private final EducationService educationService;
    private final SkillService skillService;
    private final AppConstants constants;

    public AdminController(DetailsService detailsService, SocialLinkService socialLinkService, ProjectService projectService, EducationService educationService, SkillService skillService, AppConstants constants) {
        this.detailsService = detailsService;
        this.socialLinkService = socialLinkService;
        this.projectService = projectService;
        this.educationService = educationService;
        this.skillService = skillService;
        this.constants = constants;
    }


    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        Details details = this.detailsService.findById(this.constants.DetailsID).orElse(null);
        List<SocialLink> socialLinks = this.socialLinkService.findAllSocialLinks();
        List<Project> selfProjects = this.projectService.findAllProjectsByClient("self");
        List<Project> clientProjects = this.projectService.findAllProjectsByNotClient("self");
        List<Education> educations = this.educationService.findAllEducations();
        List<Skill> languagesSkills = this.skillService.findSkillsByType("languages");
        List<Skill> frameworksSkills = this.skillService.findSkillsByType("frameworks");
        List<Skill> toolsSkills = this.skillService.findSkillsByType("tools");
        List<Skill> othersSkills = this.skillService.findSkillsByType("others");
        SkillResponseDto skills = new SkillResponseDto(languagesSkills,frameworksSkills,toolsSkills,othersSkills);
        model.addAttribute("details", details);
        model.addAttribute("socialLinks", socialLinks);
        model.addAttribute("selfProjects", selfProjects);
        model.addAttribute("clientProjects", clientProjects);
        model.addAttribute("educations", educations);
        model.addAttribute("skills",skills);
        System.out.println("Dashboard Page");
        return "admin/dashboard";
    }

    @RequestMapping("/")
    public String dashboard() {
        return "redirect:admin/dashboard";
    }

//    Details Controllers
    @RequestMapping("/update-details")
    public String updateDetails(Model model, HttpSession session) {
        Details details = this.detailsService.findById(this.constants.DetailsID).orElse(null);
        if (details == null) {
            session.setAttribute("message", new Message("Details not found!", MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("details", details);
        return "admin/edit-details";
    }

    @PostMapping("/edit-details")
    public String editDetails(Details details, Model model, HttpSession session) {
        System.out.println(details);
        this.detailsService.updateDetails(details);
        session.setAttribute("message", new Message("Details updated successfully!", MessageType.DEFAULT));
        return "redirect:/admin/dashboard#dashboard";
    }


}
