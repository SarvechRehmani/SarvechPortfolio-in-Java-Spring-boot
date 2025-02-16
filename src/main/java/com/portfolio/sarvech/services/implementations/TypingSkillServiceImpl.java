package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.models.TypingSkill;
import com.portfolio.sarvech.repositories.TypingSkillRepo;
import com.portfolio.sarvech.services.TypingSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypingSkillServiceImpl implements TypingSkillService {

    private final TypingSkillRepo typingSkillRepo;

    public TypingSkillServiceImpl(TypingSkillRepo typingSkillRepo) {
        this.typingSkillRepo = typingSkillRepo;
    }


    @Override
    public TypingSkill saveTypingSkill(TypingSkill typingSkill) {
        return null;
    }

    @Override
    public TypingSkill updateTypingSkill(TypingSkill typingSkill) {
        return null;
    }

    @Override
    public void deleteTypingSkill(Long id) {

    }

    @Override
    public TypingSkill findTypingSkillById(Long id) {
        return null;
    }

    @Override
    public List<TypingSkill> findAllTypingSkills() {
        return this.typingSkillRepo.findAll();
    }
}
