package com.example.pplite;

import jakarta.persistence.*;

@Entity
@Table(name = "EQUIPE")
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipe_id")
    private Long id;

    @Column(name = "nom_equipe", unique = true)
    private String nomEquipe;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomEquipe() { return nomEquipe; }
    public void setNomEquipe(String nomEquipe) { this.nomEquipe = nomEquipe; }
} 