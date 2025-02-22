package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.MainSkill;

import java.util.List;

public interface MainSkillService {

    void saveDefaultMainSkills();
    void saveMainSkill(MainSkill mainSkills);
    MainSkill updateMainSkills(MainSkill mainSkill);
    MainSkill findMainSkillById(long id);
    List<MainSkill> findAllMainSkills();
}
