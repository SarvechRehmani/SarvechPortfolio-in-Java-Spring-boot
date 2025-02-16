package com.portfolio.sarvech.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Details {

    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String addressLink;
    private String address;
    private String specialization;
    private int happyClients;
    private int experience;
    private String experiencedIn;
    private int projects;
    @Column(length = 2000)
        private String descriptionOne;
    @Column(length = 2000)
    private String descriptionTwo;
    private String resumeLink;

    public Details(long id) {
        this.id = id;
    }
}
