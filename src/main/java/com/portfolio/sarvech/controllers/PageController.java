package com.portfolio.sarvech.controllers;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.Details;
import com.portfolio.sarvech.services.DetailsService;
import com.portfolio.sarvech.services.SocialLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final Logger logger = LoggerFactory.getLogger(PageController.class);

    private final DetailsService detailsService;
    private final SocialLinkService socialLinkService;
    private final AppConstants constants;

    public PageController(DetailsService detailsService, SocialLinkService socialLinkService, AppConstants constants) {
        this.detailsService = detailsService;
        this.socialLinkService = socialLinkService;
        this.constants = constants;
    }

    @GetMapping
    public String index(Model model) {
        this.logger.info("Index page.");
        Details details =  this.detailsService.findById(this.constants.DetailsID).orElse(null);
        if(details == null){
            return "/maintenance";
        }
        model.addAttribute("details",details);
        return "index";
    }

    @GetMapping("home")
    public String home() {
        return "redirect:/";
    }

    @GetMapping("/login/admin-sarvech")
    public String adminLogin() {

        return "login";
    }
}
