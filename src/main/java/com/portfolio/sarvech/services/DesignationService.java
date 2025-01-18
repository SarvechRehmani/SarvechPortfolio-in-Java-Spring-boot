package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.Designation;

public interface DesignationService {

    Designation saveDesignation(Designation designation);
    Designation updateDesignation(Designation designation);
    void DeleteDesignationById(Long id);
    Designation findDesignationById(long id);
    Iterable<Designation> findAllDesignations();
}
