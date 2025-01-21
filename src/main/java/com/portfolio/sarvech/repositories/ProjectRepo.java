package com.portfolio.sarvech.repositories;

import com.portfolio.sarvech.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project , Long> {
    List<Project> findByClient(String client);
    List<Project> findByClientNot(String client);
}
