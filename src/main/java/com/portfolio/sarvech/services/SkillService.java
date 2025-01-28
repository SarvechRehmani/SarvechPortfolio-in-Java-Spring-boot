package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.Skill;

import java.util.List;

public interface SkillService {

    Skill saveSkill(Skill skill);
    Skill updateSkill(Skill skill);
    void deleteSkillById(Long id);
    Skill findSkillById(Long id);
    Skill findSkillBySkillName(String name);
    List<Skill> findAllSkills();
    List<Skill> findSkillsByType(String type);
}
