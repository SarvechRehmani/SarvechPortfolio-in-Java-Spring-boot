package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.*;
import com.portfolio.sarvech.services.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadAllDataServiceImpl implements LoadAllDataService {

    private final AppConstants constants;
    private final DetailsService detailsService;
    private final TypingSkillService typingSkillService;
    private final SocialLinkService socialLinkService;
    private final ProjectService projectService;
    private final EducationService educationService;
    private final ExperienceService experienceService;
    private final FavouriteToolsService favouriteToolsService;
    private final SkillService skillService;
    private final CertificateService certificateService;
    private final MainSkillService mainSkillService;
    public LoadAllDataServiceImpl(AppConstants constants, DetailsService detailsService, TypingSkillService typingSkillService, SocialLinkService socialLinkService, ProjectService projectService, EducationService educationService, ExperienceService experienceService, FavouriteToolsService favouriteToolsService, SkillService skillService, CertificateService certificateService, MainSkillService mainSkillService) {
        this.constants = constants;
        this.detailsService = detailsService;
        this.typingSkillService = typingSkillService;
        this.socialLinkService = socialLinkService;
        this.projectService = projectService;
        this.educationService = educationService;
        this.experienceService = experienceService;
        this.favouriteToolsService = favouriteToolsService;
        this.skillService = skillService;
        this.certificateService = certificateService;
        this.mainSkillService = mainSkillService;
    }


    @Override
    public AllDataResponse loadAllData() {

        AllDataResponse data = new AllDataResponse();
        data.setDetails(this.detailsService.findById(constants.DetailsID).orElse(null));
        data.setTypingSkills(this.typingSkillService.findAllTypingSkills());
        data.setSocialLinks(this.socialLinkService.findAllSocialLinks());
        data.setSelfProjects(this.projectService.findAllProjectsByClient("self"));
        data.setEducations(this.educationService.findAllEducations());
        data.setExperiences(this.experienceService.findAllExperiences());
        data.setFavouriteTools(this.favouriteToolsService.findAllFavouriteTools());

        List<Skill> languagesSkills = this.skillService.findSkillsByType("languages");
        List<Skill> frameworksSkills = this.skillService.findSkillsByType("frameworks");
        List<Skill> toolsSkills = this.skillService.findSkillsByType("tools");
        List<Skill> othersSkills = this.skillService.findSkillsByType("others");
        data.setSkills(new SkillResponseDto(languagesSkills, frameworksSkills, toolsSkills, othersSkills));
        data.setCertificates(this.certificateService.findAllCertificates());
        data.setMainSkills(this.mainSkillService.findAllMainSkills());
        return data;
    }

    @Override
    public Project getProjectById(long id) {
        return this.projectService.findProjectById(id);
    }
}
