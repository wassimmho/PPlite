package com.example.pplite;

import jakarta.persistence.*;

@Entity
@Table(name = "SECTION")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stade_id", nullable = false)
    private Stade stade;

    @Column(name = "nom_section")
    private String nomSection;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Stade getStade() { return stade; }
    public void setStade(Stade stade) { this.stade = stade; }

    public String getNomSection() { return nomSection; }
    public void setNomSection(String nomSection) { this.nomSection = nomSection; }
} 