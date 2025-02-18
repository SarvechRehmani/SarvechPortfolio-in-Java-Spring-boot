package com.portfolio.sarvech.repositories;

import com.portfolio.sarvech.models.MainSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainSkillRepo extends JpaRepository<MainSkills, Long> {
}
