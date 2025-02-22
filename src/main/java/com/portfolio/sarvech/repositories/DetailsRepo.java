package com.portfolio.sarvech.repositories;

import com.portfolio.sarvech.models.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepo extends JpaRepository<Details, Long> {
}
