package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.Project;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProjectService {

    Project saveProject(Project project, List<MultipartFile> files);
    Project updateProject(Project project);
    void deleteProjectById(Long id);
    Project findProjectById(Long id);
    List<Project> findAllProjectsByClient(String client);
    List<Project> findAllProjectsByNotClient(String client);
    List<Project> findAllProjects();
}
