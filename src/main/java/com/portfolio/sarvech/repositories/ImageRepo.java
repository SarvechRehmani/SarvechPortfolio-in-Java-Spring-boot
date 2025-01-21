package com.portfolio.sarvech.repositories;

import com.portfolio.sarvech.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
}
