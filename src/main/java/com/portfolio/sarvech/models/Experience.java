package com.portfolio.sarvech.models;

import jakarta.persistence.*;
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
    private String company;
    private String position;
    private String startDate;
    private String endDate;
    @Column(length = 3000)
    private String description;
    private String companyLink;

}
