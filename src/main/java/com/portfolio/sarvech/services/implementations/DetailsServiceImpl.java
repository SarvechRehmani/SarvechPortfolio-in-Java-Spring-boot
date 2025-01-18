package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.Details;
import com.portfolio.sarvech.repositories.DetailsRepo;
import com.portfolio.sarvech.services.DetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DetailsServiceImpl implements DetailsService {

    private final DetailsRepo detailsRepo;

    private final AppConstants constants;

    public DetailsServiceImpl(DetailsRepo detailsRepo, AppConstants constants) {
        this.detailsRepo = detailsRepo;
        this.constants = constants;
    }

    @Override
    public Details saveDefaultDetails() {
        Details details = new Details(this.constants.DetailsID);
        details.setFirstName("FirstName");
        details.setLastName("LastName");
        details.setEmail("email@example.com");
        details.setPhoneNumber("+924441234567");
        details.setAddress("City, Country");
        details.setSpecialization("Java Development");
        details.setHappyClients(0);
        details.setExperience(0);
        details.setExperiencedIn("Years");
        details.setProjects(0);
        return saveDetails(details);
    }

    @Override
    public Details saveDetails(Details details) {
        return this.detailsRepo.save(details);
    }

    @Override
    public Details updateDetails(Details details) {
        return this.detailsRepo.save(details);
    }

    @Override
    public Optional<Details> findById(long id) {
        return this.detailsRepo.findById(id);
    }
}
