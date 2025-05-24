package com.example.pplite;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "MATCH_FOOT")
public class MatchFoot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipe1_id", nullable = false)
    private Equipe equipe1;

    @ManyToOne
    @JoinColumn(name = "equipe2_id", nullable = false)
    private Equipe equipe2;

    @Column(name = "date_match")
    private LocalDate dateMatch;

    @Column(name = "heure")
    private LocalTime heure;

    @ManyToOne
    @JoinColumn(name = "stade_id", nullable = false)
    private Stade stade;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Equipe getEquipe1() { return equipe1; }
    public void setEquipe1(Equipe equipe1) { this.equipe1 = equipe1; }

    public Equipe getEquipe2() { return equipe2; }
    public void setEquipe2(Equipe equipe2) { this.equipe2 = equipe2; }

    public LocalDate getDateMatch() { return dateMatch; }
    public void setDateMatch(LocalDate dateMatch) { this.dateMatch = dateMatch; }

    public LocalTime getHeure() { return heure; }
    public void setHeure(LocalTime heure) { this.heure = heure; }

    public Stade getStade() { return stade; }
    public void setStade(Stade stade) { this.stade = stade; }

    public User getAdmin() { return admin; }
    public void setAdmin(User admin) { this.admin = admin; }
} 