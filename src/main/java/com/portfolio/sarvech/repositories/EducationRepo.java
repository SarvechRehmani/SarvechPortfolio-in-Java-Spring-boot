package com.portfolio.sarvech.repositories;

import com.portfolio.sarvech.models.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EducationRepo extends JpaRepository<Education, Long> {

    List<Education> findByType(String type);
}
