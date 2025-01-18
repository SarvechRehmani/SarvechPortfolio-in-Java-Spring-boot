package com.portfolio.sarvech.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Details {

    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String specialization;
    private int happyClients;
    private int experience;
    private String experiencedIn;
    private int projects;
    private String descriptionOne;
    private String descriptionTwo;




}
