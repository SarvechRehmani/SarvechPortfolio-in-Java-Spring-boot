package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.Education;
import com.portfolio.sarvech.services.EducationService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/education")
public class EducationController {

    private final Logger logger = LoggerFactory.getLogger(EducationController.class);

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    @GetMapping("/add")
    private String addEducation(Model model){
        model.addAttribute("education", new Education());
        return "admin/add-education";
    }

    @PostMapping("/save")
    private String saveEducation(Education education, HttpSession session){
        this.educationService.saveEducation(education);
        session.setAttribute("message", new Message("Education added successfully!", MessageType.SUCCESS));
        logger.info("New education saved: {}", education);
        return "redirect:/admin/education";
    }

    @GetMapping("/edit/{id}")
    private String editEducation(Model model, Long id, HttpSession session){
        Education education = this.educationService.findEducationById(id);
        if(education == null){
            logger.error("Education not found with id: {}", id);
            session.setAttribute("message", new Message("Education not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("education", education);
        return "admin/edit-education";
    }

    @PostMapping("/update/{id}")
    private String updateEducation(Education education, HttpSession session){
        Education oldEducation = this.educationService.findEducationById(education.getId());
        if(oldEducation == null){
            logger.error("Can no =t update! Because Education not found with id:: {}", education.getId());
            session.setAttribute("message", new Message("Education not found with id: "+education.getId(), MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        oldEducation.setTitle(education.getTitle());
        oldEducation.setInstitution(education.getInstitution());
        oldEducation.setStartDate(education.getStartDate());
        oldEducation.setEndDate(education.getEndDate());
        oldEducation.setLocation(education.getLocation());
        oldEducation.setGrade(education.getGrade());
        oldEducation.setTotalGrade(education.getTotalGrade());
        oldEducation.setType(education.getType());
        this.educationService.saveEducation(oldEducation);
        session.setAttribute("message", new Message("Education updated successfully!", MessageType.SUCCESS));
        logger.info("Education updated: {}", oldEducation);
        return "redirect:/admin/education";
    }

    @GetMapping("/delete/{id}")
    private String deleteEducation(Long id, HttpSession session){
        Education education = this.educationService.findEducationById(id);
        if(education == null){
            this.logger.error("Can not delete! because Education  not found with id: {}", id);
            session.setAttribute("message", new Message("Education not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        this.educationService.deleteEducationById(id);
        session.setAttribute("message", new Message("Education deleted successfully!", MessageType.SUCCESS));
        logger.info("Education deleted: {}", education);
        return "redirect:/admin/education";
    }

}
