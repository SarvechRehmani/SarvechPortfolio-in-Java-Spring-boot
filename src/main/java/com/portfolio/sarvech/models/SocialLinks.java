package com.portfolio.sarvech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class SocialLinks {
    @Id
    private long id;
    private String name;
    private String link;
}
