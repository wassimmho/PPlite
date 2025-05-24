package com.example.pplite;

import jakarta.persistence.*;

@Entity
@Table(name = "SIEGE", uniqueConstraints = @UniqueConstraint(columnNames = {"section_id", "numero_siege"}))
public class Siege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "siege_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @Column(name = "numero_siege")
    private Integer numeroSiege;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Section getSection() { return section; }
    public void setSection(Section section) { this.section = section; }

    public Integer getNumeroSiege() { return numeroSiege; }
    public void setNumeroSiege(Integer numeroSiege) { this.numeroSiege = numeroSiege; }
} 