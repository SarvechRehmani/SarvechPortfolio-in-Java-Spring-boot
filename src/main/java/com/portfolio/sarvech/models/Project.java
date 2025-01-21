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
    private long id;
    private String title;
    private String summery;
    private String description;
    private String date;
    private String client;
    private String githubLink;
    private String liveLink;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private List<image> images;
}
