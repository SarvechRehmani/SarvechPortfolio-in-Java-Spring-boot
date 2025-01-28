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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Column(length = 2000)
    private String summery;
    @Column(length = 5000)
    private String description;
    private String date;
    private String client;
    private String githubLink;
    private String liveLink;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private List<Image> images;

    public Project(String client) {
        this.client = client;
    }
}
