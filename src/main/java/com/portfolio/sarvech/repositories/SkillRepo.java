package com.portfolio.sarvech.repositories;

import com.portfolio.sarvech.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long> {
    Skill findBySkillNameIgnoreCase(String name);
    List<Skill> findBySkillType(String skillType);
}
