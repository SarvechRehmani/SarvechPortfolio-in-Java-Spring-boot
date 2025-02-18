package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.MainSkills;

import java.util.List;

public interface MainSkillService {

    void saveDefaultMainSkills();
    void saveMainSkill(MainSkills mainSkills);
    MainSkills updateMainSkills(MainSkills mainSkill);
    MainSkills findMainSkillById(long id);
    List<MainSkills> findAllMainSkills();
}
