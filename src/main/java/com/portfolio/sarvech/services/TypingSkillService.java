package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.TypingSkill;

import java.util.List;

public interface TypingSkillService {

    TypingSkill saveTypingSkill(TypingSkill typingSkill);
    TypingSkill updateTypingSkill(TypingSkill typingSkill);
    void deleteTypingSkill(Long id);
    TypingSkill findTypingSkillById(Long id);
    List<TypingSkill> findAllTypingSkills();
}
