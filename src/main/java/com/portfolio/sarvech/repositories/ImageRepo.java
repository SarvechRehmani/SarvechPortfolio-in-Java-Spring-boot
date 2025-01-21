package com.portfolio.sarvech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
}
