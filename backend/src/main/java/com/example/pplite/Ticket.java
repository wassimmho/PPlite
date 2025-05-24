package com.example.pplite;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    private String numeroPlace;
    private Double prix;
    private String statut; // e.g., "DISPONIBLE", "RESERVE", "VENDU"

    @ManyToOne
    @JoinColumn(name = "user_id") // Can be null if the ticket is not reserved
    private User user;

    // Constructors
    public Ticket() {
    }

    public Ticket(Match match, String numeroPlace, Double prix) {
        this.match = match;
        this.numeroPlace = numeroPlace;
        this.prix = prix;
        this.statut = "DISPONIBLE"; // Default status
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getNumeroPlace() {
        return numeroPlace;
    }

    public void setNumeroPlace(String numeroPlace) {
        this.numeroPlace = numeroPlace;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
} 