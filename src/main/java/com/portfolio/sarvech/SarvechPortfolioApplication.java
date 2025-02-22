package com.portfolio.sarvech;

import com.portfolio.sarvech.helper.AppConstants;
import com.portfolio.sarvech.models.Details;
import com.portfolio.sarvech.services.DetailsService;
import com.portfolio.sarvech.services.MainSkillService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SarvechPortfolioApplication implements CommandLineRunner {

	private final DetailsService detailsService;
	private  final MainSkillService mainSkillService;

	private final AppConstants constants;

	public SarvechPortfolioApplication(DetailsService detailsService, MainSkillService mainSkillService, AppConstants constants) {
        this.detailsService = detailsService;
        this.mainSkillService = mainSkillService;
        this.constants = constants;
    }

	public static void main(String[] args) {
		SpringApplication.run(SarvechPortfolioApplication.class, args);
		System.out.println("-------Project Started-------");
	}

	@Override
	public void run(String... args) throws Exception {
		boolean flag = this.detailsService.findById(this.constants.DetailsID).isPresent();
		if (!flag) {
			Details details = this.detailsService.saveDefaultDetails();
			System.out.println("Default details saved: " + details);
		}
		if(this.mainSkillService.findMainSkillById(this.constants.mainSkillOne) == null || this.mainSkillService.findMainSkillById(this.constants.mainSkillTwo) == null) {
			this.mainSkillService.saveDefaultMainSkills();
		}
	}
}
