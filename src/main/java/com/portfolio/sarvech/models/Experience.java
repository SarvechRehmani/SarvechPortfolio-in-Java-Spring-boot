package com.portfolio.sarvech.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Company name is required")
    private String company;
    @NotBlank(message = "Position is required")
    private String position;
    @NotBlank(message = "Start date is required")
    private String startDate;
    @NotBlank(message = "End date is required")
    private String endDate;
    @Column(length = 3000)
    private String description;
    @NotBlank(message = "Locations is required")
    private String location;
    private String keySkills;
    private String companyLink;

}
