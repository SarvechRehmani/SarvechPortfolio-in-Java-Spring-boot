package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.models.Designation;
import com.portfolio.sarvech.repositories.DesignationRepo;
import com.portfolio.sarvech.services.DesignationService;
import org.springframework.stereotype.Service;

@Service
public class DesignationServiceImpl implements DesignationService {

    private final DesignationRepo designationRepo;

    public DesignationServiceImpl(DesignationRepo designationRepo) {
        this.designationRepo = designationRepo;
    }

    @Override
    public Designation saveDesignation(Designation designation) {
        return this.designationRepo.save(designation);
    }

    @Override
    public Designation updateDesignation(Designation designation) {
        return this.designationRepo.save(designation);
    }

    @Override
    public void DeleteDesignationById(Long id) {
        this.designationRepo.deleteById(id);
    }

    @Override
    public Designation findDesignationById(long id) {
        return this.designationRepo.findById(id).orElse(null);
    }

    @Override
    public Iterable<Designation> findAllDesignations() {
        return this.designationRepo.findAll();
    }
}
