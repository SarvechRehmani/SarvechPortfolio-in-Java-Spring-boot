package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.repositories.ImageRepo;
import com.portfolio.sarvech.services.ImageService;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepo imageRepo;
    @Override
    public Image saveImage(Image image) {
        return this.imageRepo.save(image);
    }

    @Override
    public void deleteImage(long id) {
        this.imageRepo.findById(id).ifPresent(image -> this.imageRepo.delete(image));
    }
}
