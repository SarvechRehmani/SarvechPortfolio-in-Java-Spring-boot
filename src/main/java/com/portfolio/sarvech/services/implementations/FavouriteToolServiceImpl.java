package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.FavouriteTool;
import com.portfolio.sarvech.repositories.FavouriteToolsRepo;
import com.portfolio.sarvech.services.FavouriteToolsService;
import com.portfolio.sarvech.services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class FavouriteToolServiceImpl implements FavouriteToolsService  {

    private final FavouriteToolsRepo favouriteToolsRepo;
    private final ImageService imageService;

    private final AppConstants constants;

    public FavouriteToolServiceImpl(FavouriteToolsRepo favouriteToolsRepo, ImageService imageService, AppConstants constants) {
        this.favouriteToolsRepo = favouriteToolsRepo;
        this.imageService = imageService;
        this.constants = constants;
    }

    @Override
    public FavouriteTool saveFavouriteTool(FavouriteTool favouriteTool, MultipartFile file) {

        String fileName = this.constants.CLOUDINARY_SKILL_ICON_FOLDER + UUID.randomUUID().toString();
        String imageUrl = this.imageService.uploadImage(file, fileName, this.constants.CLOUDINARY_SKILL_ICON_FOLDER);
        favouriteTool.setLogoUrl(imageUrl);
        favouriteTool.setCloudinaryPublicId(fileName);
        return this.favouriteToolsRepo.save(favouriteTool);
    }

    @Override
    public FavouriteTool updateFavouriteTool(FavouriteTool favouriteTool) {
        return this.favouriteToolsRepo.save(favouriteTool);
    }

    @Override
    public void deleteFavouriteTool(long id) {
        FavouriteTool favouriteTool = this.favouriteToolsRepo.findById(id).orElse(null);
        if (favouriteTool != null){
            this.imageService.deleteImageFromCloudinary(favouriteTool.getCloudinaryPublicId());
        }
        this.favouriteToolsRepo.deleteById(id);
    }

    @Override
    public FavouriteTool findFavouriteToolById(Long id) {
        return this.favouriteToolsRepo.findById(id).orElse(null);
    }

    @Override
    public List<FavouriteTool> findAllFavouriteTools() {
        return this.favouriteToolsRepo.findAll();
    }
}
