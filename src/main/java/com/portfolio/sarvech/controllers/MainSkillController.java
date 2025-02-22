package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.MainSkill;
import com.portfolio.sarvech.services.MainSkillService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/main-skill")
public class MainSkillController {

    private final Logger logger = LoggerFactory.getLogger(MainSkillController.class);

    private final MainSkillService mainSkillService;

    public MainSkillController(MainSkillService mainSkillService) {
        this.mainSkillService = mainSkillService;
    }

    @GetMapping("/update/{id}")
    public String editMainSkill(@PathVariable long id, Model model, HttpSession session){
        MainSkill mainSkill = this.mainSkillService.findMainSkillById(id);
        if (mainSkill == null) {
            logger.error("Main Skill not found with id: {}", id);
            session.setAttribute("message", new Message("Main Skill not found with id: " + id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("mainSkill", mainSkill);
        return "/admin/edit-main-skill";
    }


    @PostMapping("/update/{id}")
    public String updateMainSkill(@Valid MainSkill mainSkill, BindingResult result, HttpSession session, @PathVariable long id) {
        MainSkill skill = this.mainSkillService.findMainSkillById(id);
        if (skill == null){
            session.setAttribute("message", new Message("Main Skill not found with id: "+id, MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        if (result.hasErrors()){
           session.setAttribute("message",new Message("Please Fix following errors", MessageType.ERROR));
           return "admin/edit-main-skill";
       }
        mainSkill.setId(id); // Ensure the ID is set to avoid creating a new record
        this.logger.info("Updating Main Skill.");
        mainSkillService.saveMainSkill(mainSkill);
        session.setAttribute("message", new Message("Main skill updated successfully", MessageType.SUCCESS));
        return "redirect:/admin/dashboard";
    }
}
