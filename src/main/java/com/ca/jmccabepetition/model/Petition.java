package com.ca.jmccabepetition.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "petitions")
public class Petition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    // Constructors, getters, and setters

    public Petition() {
        // Default constructor
    }

    public Petition(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Collection of signers
    @OneToMany(mappedBy = "petition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Signer> signers = new ArrayList<>();

    // Constructors, getters, and setters

    // ...

    public List<Signer> getSigners() {
        return signers;
    }

    public void addSigner(Signer signer) {
        signers.add(signer);
        signer.setPetition(this);
    }
}
