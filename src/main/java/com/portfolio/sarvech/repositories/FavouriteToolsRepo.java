package com.portfolio.sarvech.repositories;

import com.portfolio.sarvech.models.FavouriteTool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteToolsRepo extends JpaRepository<FavouriteTool, Long> {
}
