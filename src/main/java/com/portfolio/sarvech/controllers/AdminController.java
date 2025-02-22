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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    private final DetailsService detailsService;
    private final SocialLinkService socialLinkService;
    private final ProjectService projectService;
    private final EducationService educationService;
    private final ExperienceService experienceService;
    private final SkillService skillService;
    private final FavouriteToolsService favouriteToolsService;
    private final CertificateService certificateService;
    private final MainSkillService mainSkillService;
    private final ImageService imageService;
    private final AppConstants constants;

    public AdminController(DetailsService detailsService, SocialLinkService socialLinkService, ProjectService projectService, EducationService educationService, ExperienceService experienceService, SkillService skillService, FavouriteToolsService favouriteToolsService, CertificateService certificateService, MainSkillService mainSkillService, ImageService imageService, AppConstants constants) {
        this.detailsService = detailsService;
        this.socialLinkService = socialLinkService;
        this.projectService = projectService;
        this.educationService = educationService;
        this.experienceService = experienceService;
        this.skillService = skillService;
        this.favouriteToolsService = favouriteToolsService;
        this.certificateService = certificateService;
        this.mainSkillService = mainSkillService;
        this.imageService = imageService;
        this.constants = constants;
    }


    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        Details details = this.detailsService.findById(this.constants.DetailsID).orElse(null);
        List<SocialLink> socialLinks = this.socialLinkService.findAllSocialLinks();
        List<Project> selfProjects = this.projectService.findAllProjectsByClient("self");
        List<Project> clientProjects = this.projectService.findAllProjectsByNotClient("self");
        List<Education> educations = this.educationService.findAllEducations();
        List<Experience> experiences = this.experienceService.findAllExperiences();

        List<Skill> languagesSkills = this.skillService.findSkillsByType("languages");
        List<Skill> frameworksSkills = this.skillService.findSkillsByType("frameworks");
        List<Skill> toolsSkills = this.skillService.findSkillsByType("tools");
        List<Skill> othersSkills = this.skillService.findSkillsByType("others");
        SkillResponseDto skills = new SkillResponseDto(languagesSkills,frameworksSkills,toolsSkills,othersSkills);

        List<FavouriteTool> favouriteTools = this.favouriteToolsService.findAllFavouriteTools();
        List<Certificate> certificates = this.certificateService.findAllCertificates();

        List<MainSkill> mainSkills = this.mainSkillService.findAllMainSkills();

        model.addAttribute("details", details);
        model.addAttribute("socialLinks", socialLinks);
        model.addAttribute("selfProjects", selfProjects);
        model.addAttribute("clientProjects", clientProjects);
        model.addAttribute("educations", educations);
        model.addAttribute("experiences", experiences);
        model.addAttribute("skills",skills);
        model.addAttribute("favouriteTools", favouriteTools);
        model.addAttribute("certificates", certificates);
        model.addAttribute("mainSkills", mainSkills);

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
        details.setId(this.constants.DetailsID);
        this.detailsService.updateDetails(details);
        session.setAttribute("message", new Message("Details updated successfully!", MessageType.SUCCESS));
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/profile-upload")
    public String uploadNewProfile(@RequestParam("profile") MultipartFile profile, HttpSession session){
        if (profile.isEmpty()) {
            session.setAttribute("message", new Message("Please select a square image to upload!", MessageType.ERROR));
            return "redirect:/admin/dashboard";
        }
        Details details = this.detailsService.findById(this.constants.DetailsID).get();
        String profilePublicId = this.constants.CLOUDINARY_PROFILE_FOLDER + UUID.randomUUID().toString();
        String profileUrl = this.imageService.uploadImage(profile, profilePublicId, this.constants.CLOUDINARY_PROFILE_FOLDER);
        this.imageService.deleteImageFromCloudinary(details.getProfilePublicId());
        details.setProfileUrl(profileUrl);
        details.setProfilePublicId(profilePublicId);
        this.detailsService.updateDetails(details);
        session.setAttribute("message", new Message("Profile image uploaded successfully!", MessageType.SUCCESS));
        return "redirect:/admin/dashboard";
    }


}
