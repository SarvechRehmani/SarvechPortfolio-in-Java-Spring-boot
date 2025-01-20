package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.models.SocialLink;
import com.portfolio.sarvech.repositories.SocialLinkRepo;
import com.portfolio.sarvech.services.SocialLinkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialLinkServiceImpl implements SocialLinkService {

    private final SocialLinkRepo socialLinkRepo;

    public SocialLinkServiceImpl(SocialLinkRepo socialLinkRepo) {
        this.socialLinkRepo = socialLinkRepo;
    }

    @Override
    public SocialLink saveSocialLink(SocialLink socialLink) {
        return this.socialLinkRepo.save(socialLink);
    }

    @Override
    public SocialLink updateSocialLink(SocialLink socialLink) {
        return this.socialLinkRepo.save(socialLink);
    }

    @Override
    public void deleteSocialLink(Long id) {
        socialLinkRepo.deleteById(id);
    }

    @Override
    public SocialLink findSocialLinkById(Long id) {
        return this.socialLinkRepo.findById(id).orElse(null);
    }

    @Override
    public List<SocialLink> findAllSocialLinks() {
        return this.socialLinkRepo.findAll();
    }
}
