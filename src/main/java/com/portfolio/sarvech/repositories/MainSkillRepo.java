package com.portfolio.sarvech.repositories;

import com.portfolio.sarvech.models.MainSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainSkillRepo extends JpaRepository<MainSkill, Long> {
}
