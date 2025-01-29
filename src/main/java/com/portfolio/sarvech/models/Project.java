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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Project title is required")
    private String title;

    @NotBlank(message = "Start  and end date is required")
    private String date;

    @NotBlank(message = "Client name is required")
    private String client;

    @NotBlank(message = "Github Link is required")
    private String githubLink;

    private String liveLink;

    @Column(length = 2000)
    @NotBlank(message = "Summary is required")
    private String summery;

    @Column(length = 5000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "project_id")
    private List<Image> images;

    public Project(String client) {
        this.client = client;
    }
}
