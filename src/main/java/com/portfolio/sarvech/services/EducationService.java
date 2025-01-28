package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.Education;

import java.util.List;

public interface EducationService {

    Education saveEducation(Education educations);
    Education updateEducation(Education educations);
    void deleteEducationById(Long id);
    Education findEducationById(Long id);
    List<Education> findAllEducations();

}
