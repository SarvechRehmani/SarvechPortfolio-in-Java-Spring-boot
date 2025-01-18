package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.models.Details;
import com.portfolio.sarvech.services.DetailsService;
import org.springframework.stereotype.Service;

@Service
public class DetailsServiceImpl implements DetailsService {

    private final DetailsService detailsService;

    public DetailsServiceImpl(DetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @Override
    public Details saveDetails(Details details) {
        return this.detailsService.saveDetails(details);
    }
}
