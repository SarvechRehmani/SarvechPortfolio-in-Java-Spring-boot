package com.portfolio.sarvech.services.implementations;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.MainSkills;
import com.portfolio.sarvech.repositories.MainSkillRepo;
import com.portfolio.sarvech.services.MainSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainSkillServiceImpl implements MainSkillService {

    private final MainSkillRepo mainSkillRepo;
    private final AppConstants constants;

    public MainSkillServiceImpl(MainSkillRepo mainSkillRepo, AppConstants constants) {
        this.mainSkillRepo = mainSkillRepo;
        this.constants = constants;
    }

    @Override
    public void saveDefaultMainSkills() {
        MainSkills mainSkillOne = new MainSkills();
        mainSkillOne.setId(this.constants.mainSkillOne);
        mainSkillOne.setSkillTitle("Backend Development");
        mainSkillOne.setTagOne("Java");
        mainSkillOne.setTagTwo("Spring Boot");
        mainSkillOne.setShortText("Building scalable APIs, database management, authentication, and server-side logic.");
        MainSkills mainSkillTwo = new MainSkills();
        mainSkillTwo.setId(this.constants.mainSkillTwo);
        mainSkillTwo.setSkillTitle("Frontend Development");
        mainSkillTwo.setTagOne("Angular");
        mainSkillTwo.setTagTwo("TypeScript");
        mainSkillTwo.setShortText("Building dynamic and interactive web applications with Angular.");
        this.saveMainSkill(mainSkillOne);
        this.saveMainSkill(mainSkillTwo);
    }

    @Override
    public void saveMainSkill(MainSkills mainSkills) {
        this.mainSkillRepo.save(mainSkills);
    }

    @Override
    public MainSkills updateMainSkills(MainSkills mainSkill) {
        return this.mainSkillRepo.save(mainSkill);
    }

    @Override
    public MainSkills findMainSkillById(long id) {
        return this.mainSkillRepo.findById(id).orElse(null);
    }

    @Override
    public List<MainSkills> findAllMainSkills() {
        return this.mainSkillRepo.findAll();
    }
}
