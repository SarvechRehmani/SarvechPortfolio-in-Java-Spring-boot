package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.FavouriteTool;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FavouriteToolsService {

    FavouriteTool saveFavouriteTool(FavouriteTool favouriteTool, MultipartFile file);
    FavouriteTool updateFavouriteTool(FavouriteTool favouriteTool);
    void deleteFavouriteTool(long id);
    FavouriteTool findFavouriteToolById(Long id);
    List<FavouriteTool> findAllFavouriteTools();
}
