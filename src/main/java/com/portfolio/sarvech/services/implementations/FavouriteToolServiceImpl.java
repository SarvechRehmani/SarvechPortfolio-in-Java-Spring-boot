package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.models.FavouriteTool;
import com.portfolio.sarvech.repositories.FavouriteToolsRepo;
import com.portfolio.sarvech.services.FavouriteToolsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouriteToolServiceImpl implements FavouriteToolsService  {

    private final FavouriteToolsRepo favouriteToolsRepo;

    public FavouriteToolServiceImpl(FavouriteToolsRepo favouriteToolsRepo) {
        this.favouriteToolsRepo = favouriteToolsRepo;
    }

    @Override
    public FavouriteTool saveFavouriteTool(FavouriteTool favouriteTool) {
        return this.favouriteToolsRepo.save(favouriteTool);
    }

    @Override
    public FavouriteTool updateFavouriteTool(FavouriteTool favouriteTool) {
        return this.favouriteToolsRepo.save(favouriteTool);
    }

    @Override
    public void deleteFavouriteTool(Long id) {
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
