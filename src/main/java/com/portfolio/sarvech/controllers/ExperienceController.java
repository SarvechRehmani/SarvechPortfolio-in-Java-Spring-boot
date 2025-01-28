package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.Experience;
import com.portfolio.sarvech.services.ExperienceService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/experience")
public class ExperienceController {

    private final Logger logger = LoggerFactory.getLogger(ExperienceController.class);
    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/add")
    private String addExperience(Model model){
        model.addAttribute("experience",new Experience());
        return "admin/add-experience";
    }

    @PostMapping("/save")
    private String saveExperience(Experience experience, HttpSession session){
        experienceService.saveExperience(experience);
        session.setAttribute("message", new Message("Experience added successfully!", MessageType.SUCCESS));
        this.logger.info("Experience saved successfully!");
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/edit/{id}")
    private String editExperience(Model model, Long id, HttpSession session){
        Experience experience = experienceService.findExperienceById(id);
        if(experience == null){
            this.logger.error("Can not edit! Experience not found with id: {}", id);
            session.setAttribute("message", new Message("Experience not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("experience", experience);
        return "admin/edit-experience";
    }

    @PostMapping("/update/{id}")
    public String updateExperience(Experience experience, HttpSession session, Long id){
        Experience existingExperience = experienceService.findExperienceById(id);
        if(existingExperience == null){
            this.logger.error("Can not update! Experience not found with id: {}", id);
            session.setAttribute("message", new Message("Experience not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        experience.setId(id);
        experienceService.updateExperience(experience);
        session.setAttribute("message", new Message("Experience updated successfully!", MessageType.SUCCESS));
        this.logger.info("Experience updated successfully!");
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete/{id}")
    private String deleteExperience(Long id, HttpSession session){
        Experience experience = experienceService.findExperienceById(id);
        if(experience == null){
            this.logger.error("Can not delete! Experience not found with id: {}", id);
            session.setAttribute("message", new Message("Experience not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        experienceService.deleteExperienceById(id);
        session.setAttribute("message", new Message("Experience deleted successfully!", MessageType.SUCCESS));
        this.logger.info("Experience deleted successfully!");
        return "redirect:/admin/dashboard";
    }
}
