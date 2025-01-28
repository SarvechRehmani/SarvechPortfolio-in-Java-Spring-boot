package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.models.Experience;
import com.portfolio.sarvech.repositories.EducationRepo;
import com.portfolio.sarvech.repositories.ExperienceRepo;
import com.portfolio.sarvech.services.EducationService;
import com.portfolio.sarvech.services.ExperienceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final Logger logger = LoggerFactory.getLogger(ExperienceServiceImpl.class);
    private  final ExperienceRepo experienceRepo;

    public ExperienceServiceImpl(ExperienceRepo experienceRepo) {
        this.experienceRepo = experienceRepo;
    }

    @Override
    public Experience saveExperience(Experience experience) {
        return this.experienceRepo.save(experience);
    }

    @Override
    public Experience updateExperience(Experience experience) {
        return this.experienceRepo.save(experience);
    }

    @Override
    public void deleteExperienceById(Long id) {
        this.experienceRepo.deleteById(id);
    }

    @Override
    public Experience findExperienceById(long id) {
        return this.experienceRepo.findById(id).orElse(null);
    }

    @Override
    public List<Experience> findAllExperiences() {
        return this.experienceRepo.findAll();
    }
}
