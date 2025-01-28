package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.models.Skill;
import com.portfolio.sarvech.repositories.SkillRepo;
import com.portfolio.sarvech.services.SkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepo skillRepo;

    public SkillServiceImpl(SkillRepo skillRepo) {
        this.skillRepo = skillRepo;
    }

    @Override
    public Skill saveSkill(Skill skill) {
        return this.skillRepo.save(skill);
    }

    @Override
    public Skill updateSkill(Skill skill) {
        return this.skillRepo.save(skill);
    }

    @Override
    public void deleteSkillById(Long id) {
        this.skillRepo.deleteById(id);
    }

    @Override
    public Skill findSkillById(Long id) {
        return this.skillRepo.findById(id).orElse(null);
    }

    @Override
    public Skill findSkillBySkillName(String name) {
        return this.skillRepo.findBySkillNameIgnoreCase(name);
    }

    @Override
    public List<Skill> findAllSkills() {
        return this.skillRepo.findAll();
    }

    @Override
    public List<Skill> findSkillsByType(String type) {
        return this.skillRepo.findBySkillType(type);
    }
}
