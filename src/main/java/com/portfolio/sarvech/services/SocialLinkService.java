package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.SocialLink;

import java.util.List;

public interface SocialLinkService {

    SocialLink saveSocialLink(SocialLink socialLink);
    SocialLink updateSocialLink(SocialLink socialLink);
    void deleteSocialLink(Long id);
    SocialLink findSocialLinkById(Long id);
    List<SocialLink> findAllSocialLinks();

}