package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.FavouriteTool;

import java.util.List;

public interface FavouriteToolsService {

    FavouriteTool saveFavouriteTool(FavouriteTool favouriteTool);
    FavouriteTool updateFavouriteTool(FavouriteTool favouriteTool);
    void deleteFavouriteTool(Long id);
    FavouriteTool findFavouriteToolById(Long id);
    List<FavouriteTool> findAllFavouriteTools();
}
