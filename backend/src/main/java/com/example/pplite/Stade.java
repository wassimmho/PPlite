package com.example.pplite;

import jakarta.persistence.*;

@Entity
@Table(name = "STADE")
public class Stade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stade_id")
    private Long id;

    @Column(name = "nom_stade")
    private String nomStade;

    @Column(name = "capacite")
    private Integer capacite;

    @Column(name = "localisation")
    private String localisation;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomStade() { return nomStade; }
    public void setNomStade(String nomStade) { this.nomStade = nomStade; }

    public Integer getCapacite() { return capacite; }
    public void setCapacite(Integer capacite) { this.capacite = capacite; }

    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }
} 