package com.portfolio.sarvech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message="Institution name is required")
    private String institution;
    private String institutionLink;
    @NotBlank(message="Title is required")
    private String title;
    @NotBlank(message="Start date is required")
    private String startDate;
    @NotBlank(message = "End date is required")
    private String endDate;
    @NotBlank(message="Location is required")
    private String location;
    private String grade;
    private String totalGrade;
    private String gradeIn;
}
