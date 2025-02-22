package com.portfolio.sarvech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainSkill {
    @Id
    private long id;
    @NotBlank(message = "Main Skill Title is required.")
    private String skillTitle;
    @NotBlank(message = "Tag one is required.")
    private String tagOne;
    @NotBlank(message = "Tag two is required.")
    private String tagTwo;
    @NotBlank(message = "Short text is required.")
    private String shortText;
}
