package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import com.portfolio.sarvech.models.Details;
import com.portfolio.sarvech.models.SocialLink;
import com.portfolio.sarvech.services.DetailsService;
import com.portfolio.sarvech.services.SocialLinkService;
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
    private final AppConstants constants;

    public AdminController(DetailsService detailsService, SocialLinkService socialLinkService, AppConstants constants) {
        this.detailsService = detailsService;
        this.socialLinkService = socialLinkService;
        this.constants = constants;
    }


    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        Details details = this.detailsService.findById(this.constants.DetailsID).orElse(null);
        model.addAttribute("details", details);
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
        return "redirect:/admin/dashboard";
    }

//    Social Links Controllers
    @GetMapping("/social-link/add")
    public String addSocialLink(Model model, HttpSession session) {
        List<SocialLink> socialLinks = this.socialLinkService.findAllSocialLinks();
        if(socialLinks.size() >= 5){
            session.setAttribute("message", new Message("Maximum number of social links reached!", MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("socialLink", new SocialLink());
        return "admin/add-social-link";
    }

    @PostMapping("/social-link/save")
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

    @GetMapping("/update-social-link/{id}")
    public String editSocialLink(Model model, @PathVariable Long id) {
        SocialLink socialLink = this.socialLinkService.findSocialLinkById(id);
        model.addAttribute("socialLink", socialLink);
        return "admin/edit-social-link";
    }

    @PostMapping("/edit-social-link/{id}")
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

    @GetMapping("/social-link/delete/{id}")
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
