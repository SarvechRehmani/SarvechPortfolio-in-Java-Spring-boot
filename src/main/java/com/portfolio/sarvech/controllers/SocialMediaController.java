package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.SocialLink;
import com.portfolio.sarvech.services.SocialLinkService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/social-link")
public class SocialMediaController {

    private final SocialLinkService socialLinkService;

    public SocialMediaController(SocialLinkService socialLinkService) {
        this.socialLinkService = socialLinkService;
    }


    //    Social Links Controllers
    @GetMapping("/add")
    public String addSocialLink(Model model, HttpSession session) {
        List<SocialLink> socialLinks = this.socialLinkService.findAllSocialLinks();
        if(socialLinks.size() >= 5){
            session.setAttribute("message", new Message("Maximum number of social links reached!", MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("socialLink", new SocialLink());
        return "admin/add-social-link";
    }

    @PostMapping("/save")
    public String saveSocialLink(SocialLink socialLink, Model model, HttpSession session) {
        List<SocialLink> socialLinks = this.socialLinkService.findAllSocialLinks();
        if(socialLinks.size() >= 5){
            session.setAttribute("message", new Message("Maximum number of social links reached!", MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        this.socialLinkService.saveSocialLink(socialLink);
        session.setAttribute("message", new Message("Social link added successfully!", MessageType.DEFAULT));
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/update/{id}")
    public String editSocialLink(Model model, @PathVariable Long id) {
        SocialLink socialLink = this.socialLinkService.findSocialLinkById(id);
        model.addAttribute("socialLink", socialLink);
        return "admin/edit-social-link";
    }

    @PostMapping("/edit/{id}")
    public String updateSocialLink(SocialLink socialLink, Model model, HttpSession session, @PathVariable Long id) {
        SocialLink existingSocialLink = this.socialLinkService.findSocialLinkById(id);
        if(existingSocialLink == null) {
            session.setAttribute("message", new Message("Social link not found!", MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        existingSocialLink.setName(socialLink.getName());
        existingSocialLink.setIcon(socialLink.getIcon());
        existingSocialLink.setLink(socialLink.getLink());
        this.socialLinkService.updateSocialLink(existingSocialLink);
        session.setAttribute("message", new Message("Social link updated successfully!", MessageType.DEFAULT));
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete/{id}")
    public String deleteSocialLink(Model model, @PathVariable Long id, HttpSession session) {
        SocialLink socialLink = this.socialLinkService.findSocialLinkById(id);
        if(socialLink == null) {
            session.setAttribute("message", new Message("Social link not found!", MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        this.socialLinkService.deleteSocialLink(id);
        session.setAttribute("message", new Message("Social link deleted successfully!", MessageType.DEFAULT));
        return "redirect:/admin/dashboard";
    }
}
