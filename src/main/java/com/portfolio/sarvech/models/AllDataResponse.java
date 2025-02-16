package com.portfolio.sarvech.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor@ToString
public class AllDataResponse {

    private Details details;
    private List<TypingSkill> typingSkills;
    private List<SocialLink> socialLinks;
    private List<Project> selfProjects;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<FavouriteTool> favouriteTools;
    private SkillResponseDto skills;
    private List<Certificate> certificates;


}
