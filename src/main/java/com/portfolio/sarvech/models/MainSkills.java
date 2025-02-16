package com.portfolio.sarvech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String skillTitle;
    private String tagOne;
    private String tagTwo;
    private String shortText;
}
