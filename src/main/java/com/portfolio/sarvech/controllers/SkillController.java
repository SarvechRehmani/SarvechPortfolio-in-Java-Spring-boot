package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.Skill;
import com.portfolio.sarvech.services.SkillService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/skill")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/add")
    public String addSkill(Model model) {
        model.addAttribute("skill", new Skill());
        return "/admin/add-skill";
    }

    @PostMapping("/save")
    public String saveSkill(@Valid Skill skill, BindingResult result, HttpSession session) {

        Skill foundSkill = this.skillService.findSkillBySkillName(skill.getSkillName());
        if (foundSkill != null) {
            result.rejectValue("skillName", "error.skillName", "Skill is already added");
            session.setAttribute("message",new Message("Please Fix following errors", MessageType.ERROR));
            return "/admin/add-skill";
        }
        if(result.hasErrors()){
            session.setAttribute("message",new Message("Please Fix following errors", MessageType.ERROR));
            return "/admin/add-skill";
        }


        // Save skill to database
        this.skillService.saveSkill(skill);
        session.setAttribute("message", new Message("Skill added successfully!", MessageType.SUCCESS));
        return "redirect:/admin/dashboard#skills";
    }

    @GetMapping("/edit/{id}")
    public String editSkill(@PathVariable long id, Model model, HttpSession session) {
        Skill skill = this.skillService.findSkillById(id);
        if(skill == null) {
            session.setAttribute("message", new Message("Skill not found!", MessageType.ERROR));
            return "redirect:/admin/dashboard#skill";
        }
        model.addAttribute("skill", skill);
        return "/admin/edit-skill";
    }

    @PostMapping("/update/{id}")
    public String updateSkill(@Valid Skill skill, BindingResult result, HttpSession session){

        if(result.hasErrors()){
            session.setAttribute("message",new Message("Please Fix following errors", MessageType.ERROR));
            return "/admin/edit-skill";
        }

        Skill oldSkill = this.skillService.findSkillById(skill.getId());
        if(oldSkill == null) {
            session.setAttribute("message", new Message("Skill not found!", MessageType.ERROR));
            return "redirect:/admin/dashboard#skills";
        }
        // Update skill in database
        this.skillService.updateSkill(skill);
        session.setAttribute("message", new Message("Skill updated successfully!", MessageType.SUCCESS));
        return "redirect:/admin/dashboard#skills";
    }
}
