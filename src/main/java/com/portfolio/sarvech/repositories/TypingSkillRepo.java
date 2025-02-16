package com.portfolio.sarvech.repositories;

import com.portfolio.sarvech.models.TypingSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypingSkillRepo extends JpaRepository<TypingSkill, Long> {
}
