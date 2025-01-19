package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.Details;
import com.portfolio.sarvech.services.DetailsService;
import com.portfolio.sarvech.services.SocialLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.model.IModel;

@Controller
@RequestMapping("admin")
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

    @RequestMapping("/update-details")
    public String updateDetails(Model model) {
        Details details = this.detailsService.findById(this.constants.DetailsID).orElse(null);
        model.addAttribute("details", details);
        return "admin/edit-details";
    }



}
