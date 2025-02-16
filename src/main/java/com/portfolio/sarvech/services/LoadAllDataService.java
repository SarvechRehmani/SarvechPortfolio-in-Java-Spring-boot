package com.portfolio.sarvech.services;

import com.portfolio.sarvech.models.AllDataResponse;
import com.portfolio.sarvech.models.Project;

public interface LoadAllDataService {

    AllDataResponse loadAllData();

    Project getProjectById(long id);

}
