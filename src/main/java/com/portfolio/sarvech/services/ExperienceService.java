package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.Experience;

import java.util.List;

public interface ExperienceService {

    Experience saveExperience(Experience experience);
    Experience updateExperience(Experience experience);
    void deleteExperienceById(Long id);
    Experience findExperienceById(long id);
    List<Experience> findAllExperiences();
}
