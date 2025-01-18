package com.portfolio.sarvech.repositories;

import com.portfolio.sarvech.models.SocialLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialLinkRepo extends JpaRepository<SocialLink, Long> {
}
