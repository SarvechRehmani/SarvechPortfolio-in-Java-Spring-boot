package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.Image;
import com.portfolio.sarvech.models.Project;
import com.portfolio.sarvech.repositories.ProjectRepo;
import com.portfolio.sarvech.services.ImageService;
import com.portfolio.sarvech.services.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectRepo projectRepo;
    private final ImageService imageService;
    private final AppConstants constants;

    public ProjectServiceImpl(ProjectRepo projectRepo, ImageService imageService, AppConstants constants) {
        this.projectRepo = projectRepo;
        this.imageService = imageService;
        this.constants = constants;
    }

    @Override
    public Project saveProject(Project project, List<MultipartFile> files) {
        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = this.constants.CLOUDINARY_PROJECT_IMAGE_FOLDER + UUID.randomUUID().toString();
            String imageUrl = this.imageService.uploadImage(file, fileName);

            Image image = new Image();
            image.setImageUrl(imageUrl);
            image.setPublicId(fileName);
            images.add(image);
        }
        project.setImages(images);
        return projectRepo.save(project);
    }

    @Override
    public Project updateProject(Project project) {
        return this.projectRepo.save(project);
    }

    @Override
    public void deleteProjectById(Long id) {
        Project project = this.projectRepo.findById(id).orElse(null);
        if (project!= null) {
            for (Image image : project.getImages()) {
                this.imageService.deleteImage(image.getId());
            }
        }else{
            this.logger.error("Project not found with this id : {}",id);
        }
        this.projectRepo.deleteById(id);
    }

    @Override
    public Project findProjectById(Long id) {
        return this.projectRepo.findById(id).orElse(null);
    }

    @Override
    public List<Project> findAllProjectsByClient(String client) {
        return this.projectRepo.findByClient(client);
    }

    @Override
    public List<Project> findAllProjectsByNotClient(String client) {
        return this.projectRepo.findByClientNot(client);
    }

    @Override
    public List<Project> findAllProjects() {
        return this.projectRepo.findAll();
    }
}
