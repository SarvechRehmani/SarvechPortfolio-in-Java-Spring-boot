package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.models.Education;
import com.portfolio.sarvech.repositories.EducationRepo;
import com.portfolio.sarvech.services.EducationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    private final EducationRepo educationRepo;

    public EducationServiceImpl(EducationRepo educationRepo) {
        this.educationRepo = educationRepo;
    }

    @Override
    public Education saveEducation(Education educations) {
        return this.educationRepo.save(educations);
    }

    @Override
    public Education updateEducation(Education educations) {
        return this.educationRepo.save(educations);
    }

    @Override
    public void deleteEducationById(Long id) {
        this.educationRepo.deleteById(id);
    }

    @Override
    public Education findEducationById(Long id) {
        return this.educationRepo.findById(id).orElse(null);
    }


    @Override
    public List<Education> findAllEducations() {
        return this.educationRepo.findAll();
    }
}
